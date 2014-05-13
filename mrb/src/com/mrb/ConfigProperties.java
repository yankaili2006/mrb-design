/**
 * Feb 26, 2011 
 * ConfigLoader.java 
 */
package com.mrb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Administrator 1:34:52 AM 功能：读取根目录下的配置文件config.properties 单例设计模式
 */
public class ConfigProperties {

	private static Properties config = null;

	/*
	 * 单例模式
	 */
	public static Properties getInstance() {
		// 读取配置文件
		InputStream inputStream = ConfigProperties.class.getResourceAsStream("/config.properties");

		if (config == null) {
			synchronized (ConfigProperties.class) {
				if (config == null) {
					config = new Properties();
					try {
						config.load(inputStream);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return config;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
