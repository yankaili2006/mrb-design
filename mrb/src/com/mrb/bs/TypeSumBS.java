/**
 * Feb 25, 2011 
 * TypeSumBS.java 
 */
package com.mrb.bs;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mrb.bean.UserBean;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 4:30:41 PM
 */
public class TypeSumBS {

	/**
	 * 
	 */
	public TypeSumBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 根据一级分类获取每个的统计信息
	 * 
	 */
	public ArrayList getTypeSumFirstList() {
		ArrayList typeSumList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			typeSumList = (ArrayList) client.queryForList("getTypeSumFirstList");
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typeSumList;
	}

	/*
	 * 根据二级分类获取每个的统计信息
	 * 
	 */
	public ArrayList getTypeSumList(String firstTypeCode) {
		ArrayList typeSumList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			typeSumList = (ArrayList) client.queryForList("getTypeSumList", firstTypeCode);

			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typeSumList;
	}

}
