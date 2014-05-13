package com.mrb.tool.lucene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;

/*
 * 
 * 获取文件内容
 * 
 * */
public class ContentReader {

	public static String readText(File file){
		String content = "";
		Reader re = null;
		
		try {
			re = new InputStreamReader(new FileInputStream(file),"GBK"); //UTF-8是乱码，GBK和GB2312是正常的
			char[] chs = new char[1024];
			int count;
			while ((count = re.read(chs)) != -1) {
				content = content + new String(chs, 0, count);
			}
			
			re.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(content);
		return content;
	}
	
	public static void main(String[] args) {
		
		File file = new File("H:\\ebook.txt"); //utf-8.txt");//
		ContentReader.readText(file);
		
	}
	
}
