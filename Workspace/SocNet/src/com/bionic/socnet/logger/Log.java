package com.bionic.socnet.logger;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class Log {
	
	 private Logger log = null;
	 	
	   {
	    try {
	      log = Logger.getRootLogger();
//	      log.setLevel(Level.DEBUG);
	 
	      PatternLayout cfgLayout = new PatternLayout("%d{ISO8601} [%5p] [%t] [%c] %x - %m%n");
	      DailyRollingFileAppender cfgWriterApp = new DailyRollingFileAppender(
	          cfgLayout, "log/SocNet.log","'.'yyyy-MM-dd");	 
	      cfgWriterApp.setName("appender");
	      log.addAppender(cfgWriterApp);
	 
	    } catch (FileNotFoundException  e) {
	      System.err.println("The tracing file can not be generated. Probably the path does not exist.");
	    } catch (IOException e) {
	      System.err.println("The tracing file can not be generated. Probably the path does not exist.");
	    }
	  }
	
	  public Logger getLogger() {
			return log;
		}	
}
