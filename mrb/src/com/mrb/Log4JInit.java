package com.mrb;

import org.apache.log4j.PropertyConfigurator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于Log4j初始化的Servlet
 * 
 */

public class Log4JInit extends HttpServlet {

	public void init() {
		String prefix = getServletContext().getRealPath("/"); // 获取工程的根路径

		String file = getInitParameter("log4j-init-file"); // 从Web.xml中读取Log4J的配置文件
		if (file != null) {
			PropertyConfigurator.configure(prefix + file);
		} else {
			System.out.println("load " + file + " error: " + file);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
	}
}