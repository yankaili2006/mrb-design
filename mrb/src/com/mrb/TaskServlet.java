/**
 * Mar 23, 2011 
 * TaskServlet.java 
 */
package com.mrb;

import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mrb.tool.txt.xpdf2Txt;


/**
 * @author Administrator
 *4:57:07 PM
 */
public class TaskServlet extends HttpServlet {

	public void init() {
		//pdf2txt
		//doPDF2TXT();
		
		//pdf2swf
		//doPDF2SWF();
	
		
	}
	public void doPDF2TXT(){
		
		//pdf2txt
		String pdfDir = "D:\\ebook电子书"; //"D:\\ebook电子书\\Perl\\perl基础"; // "D:\\ebook电子书"; 
		xpdf2Txt pdf2Txt = new xpdf2Txt();
		pdf2Txt.convertDir(pdfDir);
		
	}
	
	public void doPDF2SWF(){
		
		System.out.println("doPDF2SWF");
		//pdf2swf
		
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
	}
}
