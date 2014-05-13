/**
 * Mar 11, 2011 
 * FileProcessUtil.java 
 */
package com.mrb.util;

import java.io.File;

/**
 * @author Administrator
 *10:50:24 PM
 *
 *替换文件名中的特殊符号，例如标点符号，中文符号等等
 *
 */
public class FileProcessUtil {

	
	/*
	 * 去除文件名中的标点符号
	 * 
	 * path 是以 \\结尾的
	 * */
	public static void removeCharactor(String path, String fileName){
		
		File oldFile = new File(path  + fileName);
		
		boolean replace = false;
		if(fileName.contains("'")){
			fileName = fileName.replace("'", "");
			replace = true;
		}
		if(fileName.contains("、")){
			fileName = fileName.replace("、", "");
			replace = true;
		}
		
		if(replace){
			File newFile = new File(path + fileName + ".pdf");
			oldFile.renameTo(newFile);

			System.out.println(path + fileName);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
