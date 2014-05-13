/**
 * Feb 24, 2011 
 * BookRequestAction.java 
 */
package com.mrb.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mrb.bean.Book2ShowBean;
import com.mrb.bs.BookBS;
import com.mrb.bs.UserClickBookBS;

/**
 * @author Administrator 9:27:43 PM
 * 
 * 浏览book的Action
 * 
 */
public class BookAction extends Action {

	/*
	 * 根据传进来的book的uuid，返回book的信息。
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) {

		// BookRequestForm reqForm = (BookRequestForm)form;
		String uuid = req.getParameter("uuid");

		BookBS bs = new BookBS();
		Book2ShowBean bean = bs.getBookByUuid(uuid);
		req.setAttribute("book", bean);

		int userId = 1;
		int bookId = bean.getId();

		UserClickBookBS ucBookBS = new UserClickBookBS();
		ucBookBS.addUserClick(userId, bookId);

		return mapping.findForward("success");
	}

}
