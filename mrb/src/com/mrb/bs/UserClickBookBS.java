/**
 * Mar 12, 2011 
 * UserClickBookBS.java 
 */
package com.mrb.bs;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mrb.bean.Book2ShowBean;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 3:18:14 PM
 */
public class UserClickBookBS {

	/*
	 * 添加点击事件
	 * 
	 */
	public void addUserClick(int userId, int bookId) {

		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("bookId", bookId);

			Date date = new Date();
			Timestamp time = new Timestamp(date.getTime());
			map.put("time", time);

			client.update("addUserClick", map);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
