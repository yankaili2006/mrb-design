/**
 * Feb 25, 2011 
 * UserBean.java 
 */
package com.mrb.bean;

/**
 * @author Administrator 11:52:49 AM
 */
public class UserBean {

	private long uid;
	private String uname;
	private String phone;
	private String pwd;
	private int type;
	private String status;
	private long date;
	private long opdate;
	private long operid;

	public void ToUserBean(RegReqBean reqBean) {

		this.uname = reqBean.getUser();
		this.phone = reqBean.getPhone();
		this.pwd = reqBean.getPwd();
	}

	/**
	 * @return the uid
	 */
	public long getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(long uid) {
		this.uid = uid;
	}

	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * @param uname
	 *            the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd
	 *            the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(long date) {
		this.date = date;
	}

	/**
	 * @return the opdate
	 */
	public long getOpdate() {
		return opdate;
	}

	/**
	 * @param opdate the opdate to set
	 */
	public void setOpdate(long opdate) {
		this.opdate = opdate;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the operid
	 */
	public long getOperid() {
		return operid;
	}

	/**
	 * @param operid the operid to set
	 */
	public void setOperid(long operid) {
		this.operid = operid;
	}
}
