/**
 * Feb 24, 2011 
 * RegisterForm.java 
 */
package com.mrb.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Administrator 9:03:50 PM
 */
public class RegisterForm extends ActionForm {

	private String logname;
	private String password;
	private String email;

	public RegisterForm() {
		logname = null;
		password = null;
		email = null;
	}

	public String getLogName() {
		return this.logname;
	}

	public void setLogName(String logname) {
		this.logname = logname;
	}

	public void setPassWord(String password) {
		this.password = password;
	}

	public String getPassWord() {
		return this.password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		logname = null;
		password = null;
		email = null;
	}

}
