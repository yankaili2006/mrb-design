/**
 * Feb 26, 2011 
 * SearchAction.java 
 */
package com.mrb.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mrb.bean.Book2ShowBean;
import com.mrb.bs.BookBS;
import com.mrb.bs.SearchBS;

/**
 * @author Administrator 12:34:59 AM
 * 
 * search的Action
 * 
 */
public class SearchAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) {

		// BookRequestForm reqForm = (BookRequestForm)form;

		String key = req.getParameter("key");
		String format = req.getParameter("booktype");

		// System.out.println(key+"===");

		BookBS bs = new BookBS();
		ArrayList bookList = null;
		if (format == null || "all".equals(format)) {
			bookList = bs.getBookListBySearch(key);
		} else {
			bookList = bs.getBookListBySearchType(key, format);
		}
		String searchKey = key;
		req.setAttribute("key", URLEncoder.encode(key));
		req.setAttribute("format", format);
		req.setAttribute("bookList", bookList);

		// 记录搜索事件
		int userId = 1;
		SearchBS searchBS = new SearchBS();
		searchBS.addKeySearch(userId, searchKey);

		return mapping.findForward("success");
	}

}
