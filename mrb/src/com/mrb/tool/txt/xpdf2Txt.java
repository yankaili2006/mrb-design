/**
 * Mar 20, 2011 
 * Pdf2Txt.java 
 */
package com.mrb.tool.txt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.mrb.Log4JConfigure;
import com.mrb.util.TimeFormatUtil;


/**
 * @author Administrator
 *7:37:45 PM
 *
 *用XPDF包转换PDF文档为TXT文档，当Txt文档在该目录下存在时，不进行转换
 */
public class xpdf2Txt {

	public static final String DEFAULT_ENCODING = "UTF-8"; // "ISO-8859-1","ISO-8859-6","US-ASCII","UTF-8","UTF-16","UTF-16BE","UTF-16LE";
	
	public static final String PATH_TO_XPDF = "F:\\flex\\xpdf3.2\\pdftotext.exe";
	

	private Logger logger = Logger.getLogger("pdf2txt");
	
	/*
	 * 构造函数
	 * */
	public xpdf2Txt(){
		//配置Log4J
		Log4JConfigure.configure();
	}
	/*
	 * 将PDF文件转换为文本文件
	 * 
	 * */
	public void convertFile(String pdfFile, String txtFile){
	 
		//pdfFile = "D:\\ebook电子书\\Perl\\perl基础\\perl基础[英文原版]OReilly.Mastring.Perl.pdf"; //"C:\\stl.pdf";
		
		//命令以及参数设置
		String[] command = new String[]{PATH_TO_XPDF, "-cfg", "xpdfrc", "-q", "-enc", "GBK", "-eol", "dos", "-nopgbrk", pdfFile, txtFile};
		//创建一个进程来执行
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//读取输出
		BufferedInputStream bis = new BufferedInputStream(p.getInputStream());
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(bis, "UTF-8");
			int ch = 0;
			while( (ch = reader.read()) != -1){
				System.out.println((char)ch);
			}
			reader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	/*
	 * 递归某个目录转换文件
	 * 
	 * */
	public void convertDir(String dirParent){
		File dir = new File(dirParent);
		if (dir.isDirectory()) {
			String list[] = dir.list();
			for (int i = 0; i < list.length; i++) {
				File file = new File(dirParent + "\\" + list[i]);
				if (file.isDirectory()) {
					convertDir(dirParent + "\\" + list[i]);
				} else {
					//开始时间
					long startTime = System.currentTimeMillis();
					//获取文件名
					String name = file.getName();

					String path = dirParent + "\\";
					path = path.replace("\\", "\\\\");

					String toPath = dirParent.replace("D:", "F:\\lucene\\txt");
					path = path.replace("\\", "\\\\");
					toPath = toPath.replace("\\", "\\\\");

					String sourceFile = path + name;

					//判断文件格式
					String lowerName = name.toLowerCase();
					if (lowerName != null && (lowerName.endsWith(".pdf"))) {

						String fileName = name.substring(0, name.lastIndexOf("."));

						//不存在目录就新建
						File dest = new File(toPath);
						if(!dest.exists()){
							dest.mkdirs();
						}
						// Txt文件已经存在
						String destFileName = fileName + ".txt";
						if (new File(toPath + "\\\\" + destFileName).exists()) {
							continue;
						}
						
						//文件转换
						convertFile(sourceFile, toPath + "\\\\" + destFileName);
						
						//结束时间
						long endTime = System.currentTimeMillis();
						long costMillis = endTime - startTime;
						//写日志
						logger.info("["+TimeFormatUtil.ms2String(costMillis)+"] " + sourceFile);
						
					} else {
						continue;
					}
				}
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String pdfDir = "D:\\ebook电子书"; //"D:\\ebook电子书\\Perl\\perl基础"; // "D:\\ebook电子书"; 
		xpdf2Txt pdf2Txt = new xpdf2Txt();
		pdf2Txt.convertDir(pdfDir);

	}

}
