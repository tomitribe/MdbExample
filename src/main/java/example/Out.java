package com.example;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.awt.Color;

/*
 * The only purpose of this class is to hide the output channel so it can be changed if
 * needed from System.out to something else (e.g. JUL) and to provide some formatting 
 * including font color to help distinguish the output from other output.
 */
public class Out {
	
	public static void println(Object obj, TextMessage msg, Color color) {
		
		try {
			String txt = msg.getText();
			Out.println(obj,txt, color);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

    public static void println(Object obj, String txt, Color color) {
    	
		String temp = obj.toString();
		int start = temp.indexOf('@');
		String mbdID = temp.substring(start, start + 3);
		
		String defaultColor = (char)27 + "[0m";
		String txtColor = defaultColor;
		if(color == Color.orange) {
			txtColor = (char)27 + "[33m";
		}else if (color == Color.green) {
			txtColor = (char)27 + "[32m";
		}else if (color == Color.blue) {
			txtColor = (char)27 + "[34m";
		}

		// Change this to the desired output (e.g. JUL)
    	System.out.println(txtColor + mbdID + " - Received = " + txt + defaultColor);

    }
}
