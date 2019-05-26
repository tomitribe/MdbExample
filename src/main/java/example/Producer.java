/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example;

// ActiveMQ JMS Provider
//import org.apache.qpid.jms.JmsConnectionFactory;

//ActiveMQ TCP
import org.apache.activemq.ActiveMQConnectionFactory;
        

// JMS API types
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.DeliveryMode;
import javax.jms.TextMessage;
import javax.jms.MessageProducer;

import java.io.Console;

class Producer {

    public static void main(String[] args) throws Exception {

    	/*  
    	 *  Every JMS provider (every library that implements the JMS API) 
    	 *  will have its own implementation of the javax.jms.ConnectionFactory. 
    	 * 
    	 *  The purpose of the ConnectionFactory is to create a network connection 
    	 *  to a specific JMS broker, such as ActiveMQ, or a specific protocol,
    	 *  such as TCP.  This allows the JMS library to send and receive messages
    	 *  over a network from the broker.
    	 * 
    	 *  In this case we are using the Apache TCP JMS library which is specific 
    	 *  to the protocol, OpenWire. OpenWire is only one of ten protocols currently supported by
    	 *  ActiveMQ.
    	 */
    	 
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection("admin", "password");
        connection.start();
        
        /*    
    	 *  Every JMS Connection can have multiple sessions which manage things like
         *  transactions and message persistence separately.  In practice multiple sessions
         *  are not used much by developers but may be used in more sophisticated
         *  application servers to conserve resources.
         */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        /*    
    	 *  A Destination is a Topic, SpiderTopic, to which a web page URL is sent.  
         */
        Destination destination = destination = session.createTopic("SpiderTopic");        	
        
        /*    
    	 *  A MessageProducer is specific to a destination - it can only send 
         *  messages to one Topic or Queue. 
		 */
        MessageProducer producer = session.createProducer(destination);

        
        /*   
    	 *  This section of code simply reads input from the console and then sends that
         * input as JMS Message to the "SpiderTopic" on the ActiveMQ broker.
         */
        Console c = System.console();
        String response;
        do {
        	
            response = c.readLine("Enter message: ");
            
            /* Every time you want to send a message to a Queue or Topic you have to create
             * a Message object. There are a few different kinds (text, binary, object, 
             * IO stream). In this case we are just sending a text message which is one of
             * the simplest. JMS Topics and Queues support the same message types.
             */
            TextMessage msg = session.createTextMessage(response);
            producer.send(msg);
            
        } while (!response.equalsIgnoreCase("SHUTDOWN"));
        
        /* As is the case with most enterprise resources, you want to shut a JMS connection
         * down when you are done using it.  This tells the JMS broker that it can free
         * up the network resources used for that connection making the whole system more 
         * scalable.
         */
        connection.close();
        System.exit(1);
    }
}