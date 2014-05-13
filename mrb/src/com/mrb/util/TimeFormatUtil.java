/**
 * Mar 22, 2011 
 * TimeFormatUtil.java 
 */
package com.mrb.util;

/**
 * @author Administrator
 *12:15:23 AM
 */
public class TimeFormatUtil {

	
	public static String ms2String(long ms){
		String result = "";
		if(ms < 0){
		}
		else if(ms <1000){ //小于1秒
			result = ms+" ms";
		}
		else if(ms <60000){ //小于一分钟
			result = ms/1000+" s "+ (ms%1000>0?ms%1000+" ms":"");
		}
		else if(ms <3600000){ //小于一个小时
			result = ms/60000+" m "+ ((ms%60000)/1000>0?(ms%60000)/1000+" s ":"") + ((ms%60000)%1000>0?(ms%60000)%1000 +" ms":"");
		}
		else{ //大于一个小时
			result = ms/3600000+" h "+ ((ms%3600000)/60000>0?(ms%3600000)/60000+" m ":"")+ ( ((ms%3600000)%60000)/1000>0?((ms%3600000)%60000)/1000 +" s ":"" )+ ( ((ms%3600000)%60000)%1000>0?((ms%3600000)%60000)%1000+" ms":"");
		}
		
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TimeFormatUtil util = new TimeFormatUtil();
		System.out.println(util.ms2String(3626050));

	}

}
