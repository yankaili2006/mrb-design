/**
 * Feb 25, 2011 
 * Upload.java 
 */
package com.mrb;

/**
 * @author Administrator 10:59:34 PM 上传文件
 * 
 * 文件上传的Servlet
 */
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.log4j.Logger;

import com.mrb.bs.BookTypeBS;
import com.mrb.util.FileProcessUtil;

/*
 * 上传文件处理
 * 
 */
public class Upload extends HttpServlet {

	private String uploadPath = "";
	private String swfPath = "";
	private String tempPath = ""; // 

	private String pdfPath = "";

	/*
	 * 处理用户上传文件的请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			request.setCharacterEncoding("utf-8");

			Logger logger = Logger.getLogger(Upload.class.getName());

			int userId = 0;
			String firstType = "";
			String secondType = "";

			String summary = "";
			String title = "";
			String format = "";

			long size = 0l;

			DiskFileUpload fu = new DiskFileUpload();

			fu.setSizeMax(4194304);
			fu.setSizeThreshold(4096);
			fu.setRepositoryPath(tempPath);

			String name = "";

			List fileItems = fu.parseRequest(request);
			Iterator i = fileItems.iterator();

			BookTypeBS bs = new BookTypeBS();

			String savePath = "";
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();

				if ("file".equals(fi.getFieldName())) {

					// filename 这里是全路径
					String fileName = fi.getName();
					name = fileName.substring(fileName.lastIndexOf("\\") + 1);

					// System.out.println("fileName:"+fileName);

					// 获取文件大小
					size = fi.getSize();

					if (fileName != null) {
						fi.write(new File(savePath + "\\" + name));
					}

					// 取得上传文件的格式
					if (name.contains(".")) {
						format = name.substring(name.lastIndexOf(".") + 1).toLowerCase(); // format
					}
				} else {
					// 获取文件名
					String fieldName = fi.getFieldName();
					// 字段:一级分类信息
					if ("typeFirst".equals(fieldName)) {
						firstType = fi.getString("UTF-8");
						// 字段:二级分类信息
					} else if ("typeSecond".equals(fieldName)) {
						secondType = fi.getString("UTF-8");

						// 根据类型获取应该存储位置的相对路径
						String relativePath = bs.getPathWithCode(secondType);
						savePath = pdfPath + "\\" + relativePath;
						// System.out.println(savePath + "==============");
					} else if ("summary".equals(fieldName)) {
						summary = fi.getString("UTF-8");
					} else if ("title".equals(fieldName)) {
						title = fi.getString("UTF-8");
					}
				}// end of else

			}

			// System.out.println("upload:" + savePath);
			// 写入上传日志
			logger.info(userId + " upload file:" + savePath);

			FileProcessUtil.removeCharactor(uploadPath, name);
			// format是小写
			if (format.endsWith("pdf")) {
				// 创建一个线程来做PDF到SWF的转换
				String destName = name.substring(0, name.lastIndexOf(".")) + ".swf";

				// 获得分类信息的ID号
				int codeInt = bs.getIdWithFullCode(secondType);
				UUID uuid = UUID.randomUUID();

				// 写入一条信息到数据库中

				// start toTxt thread
				// /Pdf2Txt p2Txt = new Pdf2Txt(name, name);
				// /p2Txt.start();

			}

			response.sendRedirect("/mrb/index.jsp");

		} catch (Exception e) {

		}

	}

	public void init() throws ServletException {

		Properties config = ConfigProperties.getInstance();

		uploadPath = config.getProperty("uploadPath");
		swfPath = config.getProperty("swfPath");
		tempPath = uploadPath + config.getProperty("uploadTempFileName");

		/*
		 * try { pdfPath = new
		 * String(config.getProperty("pdfPath").getBytes("Latin1"), "UTF-8"); }
		 * catch (UnsupportedEncodingException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		if (pdfPath == null || "".equals(pdfPath)) {

			pdfPath = "D:\\ebook电子书";

		}

		if (!new File(uploadPath).isDirectory()) {
			new File(uploadPath).mkdirs();
		}

		if (!new File(swfPath).isDirectory()) {
			new File(swfPath).mkdirs();
		}

		if (!new File(tempPath).isDirectory()) {
			new File(tempPath).mkdirs();
		}

		if (!new File(pdfPath).isDirectory()) {
			new File(pdfPath).mkdirs();
		}
	}

}
