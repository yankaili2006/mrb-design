/**
 * Feb 26, 2011 
 * DownloadAction.java 
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

import com.mrb.bean.Book2ShowBean;
import com.mrb.bs.BookBS;
import com.mrb.bs.UserDownloadBookBS;

/**
 * @author Administrator 7:04:12 PM
 * 
 * 下载文件的Action
 */
public class DownloadAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String uuid = request.getParameter("uuid");

		BookBS bs = new BookBS();
		Book2ShowBean bean = bs.getBookByUuid(uuid);
		String fileName = bean.getPath();

		System.out.println("download:" + fileName);

		fileName = fileName.replace("\\", "\\\\");

		File f = new File(fileName);
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

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-msdownload;charset=utf-8");
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(f.getName(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		OutputStream out = null;
		try {
			out = response.getOutputStream();
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

		// 记录下载事件
		int userId = 1;
		int bookId = bean.getId();
		UserDownloadBookBS udBookBS = new UserDownloadBookBS();
		udBookBS.addUserDownload(userId, bookId);

		return null;// mapping.findForward("success");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
