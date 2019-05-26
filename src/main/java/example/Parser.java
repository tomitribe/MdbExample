package com.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/*
 * This class uses the JSoup HTML parser library to obtain a list of all the 
 * image URLs from the <img src/> elements and the URLs to other web pages 
 * from the <a href/> elements and then stores them as List<String> types in its
 * instance variables.
 */
public class Parser {
	
	List<String> aHrefs;
	List<String> imgSrc;
	
	public Parser(URL url) throws IOException {
		
		Document doc = Jsoup.connect(url.toString()).get();
		
		aHrefs = doc.select("a").eachAttr("abs:href");
		imgSrc = doc.select("img").eachAttr("abs:src");
		
		
	}
	
	public static void main(String [] args) throws MalformedURLException, IOException {
		Parser p = new Parser(new URL("http://www.tomitribe.com"));
		
		for(String urlstrinig : p.aHrefs) {
			System.out.println(urlstrinig);
		}
		
		System.out.println("********************");
		System.out.println("********************");
		
		
		for(String urlstrinig : p.imgSrc) {
			System.out.println(urlstrinig);
		}
	}

}
