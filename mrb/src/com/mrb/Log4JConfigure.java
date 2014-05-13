/**
 * Mar 22, 2011 
 * Log4JConfigure.java 
 */
package com.mrb;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author Administrator
 *12:35:59 AM
 */
public class Log4JConfigure {

	private static final String config = "log4j.properties";
	
	public static void configure(){
		
		//配置Log4J
		String path = new File("").getAbsolutePath()+".\\WebRoot\\WEB-INF\\";
		PropertyConfigurator.configure(path + config);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
