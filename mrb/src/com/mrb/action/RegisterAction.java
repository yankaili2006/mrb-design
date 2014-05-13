/**
 * Feb 24, 2011 
 * RegisterAction.java 
 */
package com.mrb.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.google.gson.Gson;
import com.mrb.bean.RegReqBean;
import com.mrb.bean.RegResBean;
import com.mrb.bs.UserBS;

/**
 * @author Administrator 9:06:26 PM
 * 
 * 用户注册的Action
 */
public class RegisterAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());

		RegReqBean json = null;
		Gson gson = new Gson();
		try {
			json = gson.fromJson(req.getReader(), RegReqBean.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		UserBS bs = new UserBS();
		if (json != null) {
			bs.regUser(json);
			log.debug(json.getUser());
		}
		log.debug("user:" + req.getParameter("user"));

		log.debug("json.getUser()");

		log.debug("readJsonFromRequestBody: " + readJsonFromRequestBody(req));
		try {
			log.debug("readFromReq:" + readFromReq(req.getReader()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		RegResBean resBean = new RegResBean();
		resBean.setCode("0000");
		resBean.setMsg("注册成功");

		PrintWriter pw = null;
		res.setContentType("text/html;charset=UTF-8");
		try {
			pw = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (pw != null) {
			pw.print(gson.toJson(resBean, RegResBean.class));
			pw.close();
		}

		return null;
	}

	private String readFromReq(BufferedReader reader) {

		StringBuilder builder = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return builder.toString();
	}


	private String readJsonFromRequestBody(HttpServletRequest request) {
		StringBuffer jsonBuf = new StringBuffer();
		char[] buf = new char[2048];
		int len = -1;
		try {
			BufferedReader reader = request.getReader();
			while ((len = reader.read(buf)) != -1) {
				jsonBuf.append(new String(buf, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonBuf.toString();
	}
}
