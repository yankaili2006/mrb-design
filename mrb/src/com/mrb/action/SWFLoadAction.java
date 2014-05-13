/**
 * Mar 17, 2011 
 * SWFLoadAction.java 
 */
package com.mrb.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Administrator
 *11:45:10 AM
 */
public class SWFLoadAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String fileName = request.getParameter("fileName");

		String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 5.5\\webapps\\mrb\\swf";
		
		String fullPath = path +"\\"+ fileName;
		
		System.out.println("download:"+fullPath);

		fullPath = fullPath.replace("\\", "\\\\");

		File f = new File(fullPath);
		if (!f.exists()) {
			try {
				response.sendError(404, "File not found!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		BufferedInputStream br = null;
		try {
			br = new BufferedInputStream(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		byte[] buf = new byte[1024];
		int len = 0;
		response.reset(); // 非常重要
		boolean isOnLine = false;

		/*response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-msdownload;charset=utf-8");
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(f.getName(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/

		OutputStream out = null;
		try {
			out = response.getOutputStream();
			int total = 128;
			int fromP = 5;
			int toP = 16;
		
			
			String a = "10086100861008610086"; //4 10086
			out.write(a.getBytes());
			
			out.write( SWFLoadAction.int2bytes(total) );
			out.write( SWFLoadAction.int2bytes(fromP) );
			out.write( SWFLoadAction.int2bytes(toP) );
			
			while ((len = br.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.flush();
			br.close();
			out.close();
		} catch (IOException e) {
			try {
				if (out != null) {
					out.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (Exception ex) {

			}
		}
		
		return null;// mapping.findForward("success");
	}
	
	static byte[] int2bytes(int num)
	{
	       byte[] b=new byte[4];
	       int mask=0xff;
	       for(int i=0;i<4;i++){
	            b[i]=(byte)(num>>>(24-i*8));
	       }
	      return b;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
