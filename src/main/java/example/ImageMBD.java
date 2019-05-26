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
 * MBD responds to messages sent to a Queue named "ImageQueue". Using the the 
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
					propertyValue = "ImageQueue") 
		}
	)

/*
 * The ImageMBD receives a URL for an image and then simply outputs the URL along with some 
 * formatting provided by the Out class.
 * 
 * This MBD could be used to do just about anything with images such as store them or alter 
 * them in some way.  For example, it might be used to create thumbnail images.
 */
public class ImageMBD implements MessageListener {

	/*
	 * The javax.jms.MessageListener interface must be implemented by MDBs. It declares 
	 * the onMessage(Message message) method which is called when a messages is delivered to 
	 * MBD from the Topic or Queue to which it is subscribed.
	 * 
	 * 
	 * In the case of a Queue, every MDB listening to the same Queue must wait in a queue 
	 * (thus the name) to recieve a message. When a message is delivered to the Queue the 
	 * MDB in the front gets  the message and moves to the back of the queue so that the 
	 * next MDB in line gets the next message.
	 */
    public void onMessage(Message message) {
			
        /* 
         * Messages received from a Queue are delivered as a Message object. 	
         * There are a few different kinds Message objects (text, binary, object, 
         * IO stream). In this case we are expecting a text message which is one of
         * the simplest. JMS Topics and Queues support the same message types.
         */
		TextMessage msg = (TextMessage)message;

		Out.println(this, msg, Color.orange);

    }
}
