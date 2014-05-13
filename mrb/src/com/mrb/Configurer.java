package com.mrb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurer {
	InputStream inputStream = null;
	Properties p = null;

	public void configure() {

		inputStream = this.getClass().getClassLoader().getResourceAsStream(
				"ipConfig.properties");
		p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("ip:" + p.getProperty("ip") + ",port:"
				+ p.getProperty("port"));

	}

	
	public void close() {
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.clear();

	}
}
