## Overview

This is an example of how to use MDBs in TomEE with ActiveMQ

## Prereqs

- Install Java SDK
- Install [Maven](http://maven.apache.org/download.html) 

## Building

Run:

    mvn install

## Running the Examples

This example requires the installation of the TomEE Plume server, the ActiveMQ 5 broker, 
and a command line to run both maven builds and to run a standalone Java application,
the Producer.java

The purpose of this example is to demonstrate how MDBs can receive messages from outside 
the TomEE server and how MBDs can cooperate across Topics and Queues to message each other 
creating a flow of logic from one MBD to the next.

1. Download and un-package the the ActiveMQ 5 binary and run the following command to get it
started.

	$ bin/activemq console
	
2. Download, un-package the TomEE Plume binary distribution.
3. Copy the tomee.xml and logging.properties files into the tomee/conf directory 
	overwriting the existing files.
4. Copy the SpiderEAR.ear into the tomee/webapps directory
5. Start the TomEE application server using the following command.
	
	$ bin/catalina.sh run
	
6. Open a console windows to this directory and run the following Maven build command

	$ mvn clean install

7. Start the Producer Java application.

    $ java -cp target/jms-example-SNAPSHOT.jar example.Publisher
    
8. At the message prompt type in a web URL (e.g. http://www.tomitribe.com)
9. Watch the output from the TomEE console and see all of the image and page links printed
	out in Blue, Orange, and Green.


