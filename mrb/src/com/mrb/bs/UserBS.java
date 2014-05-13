/**
 * Feb 25, 2011 
 * BookBS.java 
 */
package com.mrb.bs;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mrb.bean.Book2ShowBean;
import com.mrb.bean.RegReqBean;
import com.mrb.bean.UserBean;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 7:24:13 PM
 */
public class UserBS {

	/**
	 * 
	 */
	public UserBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新用户
	 */
	public Boolean regUser(RegReqBean reqBean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			UserBean bean = new UserBean();
			bean.ToUserBean(reqBean);

			long uid = System.currentTimeMillis() % 1000000;
			bean.setUid(uid);
			bean.setStatus("Z");
			bean.setType(0);
			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());
			bean.setDate(Long.valueOf(now));
			bean.setOpdate(Long.valueOf(now));
			bean.setOperid(1L);

			client.update("addUser", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 通过uid获取用户信息
	 */
	public UserBean getUserById(Long id) {
		UserBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (UserBean) client.queryForObject("getUserById", id);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/*
	 * 获取用户列表
	 */
	public ArrayList getUserList() {
		ArrayList userList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			userList = (ArrayList) client.queryForList("getUserList");
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	/*
	 * 更新用户信息
	 */
	public Boolean updateUser(UserBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateUser", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList getBookListBySearchType(String key, String format) {
		ArrayList bookByTypeList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap map = new HashMap();
			map.put("key", key);
			map.put("format", format);
			bookByTypeList = (ArrayList) client.queryForList(
					"getBookListBySearchType", map);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				client.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bookByTypeList;
	}

	public Integer getBookListBySearchTypeCount(String key, String format) {
		Integer count = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();

		try {
			client.startTransaction();
			HashMap map = new HashMap();
			map.put("key", key);
			if ((format != null) && (format.length() > 0)) {
				if ("all".equals(format)) {
					//
				} else {
					map.put("format", format);
				}
			}
			count = (Integer) client.queryForObject(
					"getBookListBySearchTypeCount", map);

			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				client.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UserBS bs = new UserBS();
		RegReqBean reqBean = new RegReqBean();
		reqBean.setUser("yankaili2006");
		reqBean.setPwd("000000");
		reqBean.setPhone("15901411984");

		//bs.regUser(reqBean);

		System.out.println(bs.getUserById(635281L).getUname());

		System.out.println(bs.getUserList().size());

		UserBean bean = new UserBean();
		bean.setUid(950133);
		bean.setUname("coola58");
		SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = dfm.format(new Date());
		bean.setOpdate(Long.valueOf(now));
	
		bs.updateUser(bean);
	}

}
