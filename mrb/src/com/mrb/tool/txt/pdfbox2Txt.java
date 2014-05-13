/**
 * Mar 20, 2011 
 * pdf2Txt.java 
 */
package com.mrb.tool.txt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import com.mrb.Log4JConfigure;
import com.mrb.util.TimeFormatUtil;

/**
 * @author Administrator
 * 
 * 用Java的PdfBox包转换PDF文档为TXT文档，当Txt文档在该目录下存在时，不进行转换
 *7:07:40 PM
 */
public class pdfbox2Txt {

	public static final String DEFAULT_ENCODING = "UTF-8"; // "ISO-8859-1","ISO-8859-6","US-ASCII","UTF-8","UTF-16","UTF-16BE","UTF-16LE";
	

	Logger log = Logger.getLogger("pdf2txt");
	
	/**
	 * 构造函数
	 */
	public pdfbox2Txt() {
		//配置Log4J
		Log4JConfigure.configure();
	}
	
	public void convertFile(String pdfFile, String txtFile){
		//是否排序
		boolean sort = false;
		String encoding = "UTF-8";
		
		int startPage = 1;
		int endPage = Integer.MAX_VALUE;
		
		Writer output = null;
		
		File inFile = new File(pdfFile);
		//内存中存储的PDF Document
		PDDocument document = null;
		try {
			//load pdf file
			document = PDDocument.load(inFile.getAbsoluteFile());
			
			//参数中不提供输出文件名时，就默认与原文件名相同，后缀不同
			String txtFileName = txtFile;
			if ( (txtFile == null) || "".equals(txtFile) ) {
				String fileName = inFile.getAbsolutePath() + inFile.getName();
				if((fileName.length()>4)){
					txtFileName = fileName.substring(0, fileName.length() - 4) + ".txt";
				}
			}

			//output writer
			output = new OutputStreamWriter(new FileOutputStream(txtFileName), encoding);
			
			//strip text from the PDF file
			PDFTextStripper stripper = new PDFTextStripper();

			// 设置是否排序
			stripper.setSortByPosition(sort);
			// 设置起始页
			stripper.setStartPage(startPage);
			// 设置结束页
			stripper.setEndPage(endPage);

			// 调用PDFTextStripper的writeText提取并输出文本
			stripper.writeText(document, output);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				// 关闭输出流
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (document != null) {
				// 关闭PDF Document
				try {
					document.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * 递归某个目录，转换文件
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
					
					/* 存放生成的TXT的路径 */
					String toPath = dirParent.replace("H:", "E:\\lucene\\txt");
					
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
						
						//System.out.println(sourceFile);
						//System.out.println(path + "\\\\" + destFileName);
						//结束时间
						long endTime = System.currentTimeMillis();
						long costMillis = endTime - startTime;
						//写日志
						log.info("["+TimeFormatUtil.ms2String(costMillis)+"] " + sourceFile);
						
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
		String pdfDir = "H:\\ebook电子书\\Perl\\perl基础"; // "D:\\ebook电子书"; 
		pdfbox2Txt pdf2Txt = new pdfbox2Txt();
		pdf2Txt.convertDir(pdfDir);

	}

}
