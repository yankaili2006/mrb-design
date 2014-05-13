/**
 * Mar 12, 2011 
 * SearchBS.java 
 */
package com.mrb.bs;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 4:23:06 PM
 */
public class SearchBS {

	/*
	 * 添加搜索事件
	 * 
	 */
	public void addKeySearch(int userId, String inputKey) {

		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			Map map = new HashMap();
			map.put("userId", userId);
			map.put("inputKey", inputKey);

			// System.out.println("key:"+inputKey);

			Date date = new Date();
			Timestamp time = new Timestamp(date.getTime());
			map.put("time", time);

			client.update("addKeySearch", map);
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
