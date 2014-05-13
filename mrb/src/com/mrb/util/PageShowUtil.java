/**
 * Mar 11, 2011 
 * PageShowUtil.java 
 */
package com.mrb.util;

/**
 * @author Administrator
 *8:52:33 PM
 *
 *将文档的字节大小数字，转换为KB,MB和GB
 */
public class PageShowUtil {

	public static String number2GMK(long num){
		String gmk = "";
		if(num > 0 ){
			if(num < 1024){
				gmk = num +" bytes";
			}
			else if(num < 1048576){
				gmk = (num-1)/1024 +1 +" K";
			}
			else if(num < 1073741824){
				gmk = (num-1)/1048576 +1 +" M";
			}
			else{
				gmk = (num-1)/1073741824 +1 +" G";
			}
		}
		
		return gmk;
		
	}
	
	public static String number2GMK(int num){
		String gmk = "";
		if(num > 0 ){
			if(num < 1024){
				gmk = num +" bytes";
			}
			else if(num < 1048576){
				gmk = (num-1)/1024 +1 +" K";
			}
			else if(num < 1073741824){
				gmk = (num-1)/1048576 +1 +" M";
			}
			else{
				gmk = (num-1)/1073741824 +1 +" G";
			}
		}
		
		return gmk;
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
