/**
 * Feb 26, 2011 
 * BookTypeAjaxAction.java 
 */
package com.mrb.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mrb.bean.BookType2ShowBean;
import com.mrb.bs.BookBS;
import com.mrb.bs.BookTypeBS;

/**
 * @author Administrator 10:27:53 AM
 * 
 * 上传文件中，获取Book type的Action
 * 
 */
public class BookTypeAjaxAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		BookTypeBS bs = new BookTypeBS();

		String typeFirstCode = request.getParameter("typeFirstCode");

		// System.out.println("typeFirstCode:"+typeFirstCode);

		ArrayList typeSecondList = bs.getSecondBookTypeByFirst(typeFirstCode);

		PrintWriter pw = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			pw = response.getWriter();
			if (typeSecondList == null || typeSecondList.size() <= 0) {
				pw.print("noType");
			} else {
				String out = "";
				for (int i = 0; i < typeSecondList.size(); i++) {
					BookType2ShowBean bean = (BookType2ShowBean) typeSecondList.get(i);
					out += bean.getCode() + "," + bean.getName() + ",";
					// System.out.print(bean.getCode()+","+bean.getName());
				}
				out = out.substring(0, out.length() - 1);
				pw.print(out);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.close();

		return null;
	}

}
