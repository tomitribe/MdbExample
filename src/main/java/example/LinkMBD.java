package com.example;

import java.awt.Color;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/*
 * The @MessageDriven annotation is pretty powerful and can replace much of the configuration
 * formally done either using the API or XML files.  In this case we are indicating that this
 * MBD responds to messages sent to a Queue named "LinkQueue". Using the the 
 * @ActiveConfigProperty you can specify everything from Acknowledgement Mode to Message 
 * Selectors.
 */
@MessageDriven(
		activationConfig = { 
			@ActivationConfigProperty(
					propertyName = "destinationType", 
					propertyValue = "javax.jms.Queue"),
			@ActivationConfigProperty( 
					propertyName = "destination", 
					propertyValue = "LinkQueue") 
		}
	)
public class LinkMBD implements MessageListener {

	/*
	 * The LinkMBD receives a URL for web page and then simply outputs the URL along with some 
	 * formatting provided by the Out class.
	 * 
	 * This MBD could be used to do just about anything with URL links. It could spider the web 
	 * page,log the URLs, modify the paths, etc.
	 */
    public void onMessage(Message message) {
		
        /* 
         * Messages received from a Queue are delivered as a Message object. 	
         * There are a few different kinds Message objects (text, binary, object, 
         * IO stream). In this case we are expecting a text message which is one of
         * the simplest. JMS Topics and Queues support the same message types.
         */
		TextMessage msg = (TextMessage)message;

		Out.println(this, msg, Color.green);

    }

}
