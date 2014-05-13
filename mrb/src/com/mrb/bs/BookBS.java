/**
 * Feb 25, 2011 
 * BookBS.java 
 */
package com.mrb.bs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mrb.bean.Book2ShowBean;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 7:24:13 PM
 */
public class BookBS {

	/**
	 * 
	 */
	public BookBS() {
		// TODO Auto-generated constructor stub
	}

	public Book2ShowBean getBookByUuid(String uuid) {
		Book2ShowBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (Book2ShowBean) client.queryForObject("getBookByUuid", uuid);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	public ArrayList getBookListBySearch(String key) {
		ArrayList bookByTypeList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bookByTypeList = (ArrayList) client.queryForList("getBookListBySearch", key);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookByTypeList;
	}

	public ArrayList getBookListBySearchType(String key, String format) {
		ArrayList bookByTypeList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap map = new HashMap();
			map.put("key", key);
			map.put("format", format);
			bookByTypeList = (ArrayList) client.queryForList("getBookListBySearchType", map);
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
			count = (Integer) client.queryForObject("getBookListBySearchTypeCount", map);

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

	public ArrayList getBookPageBySearchType(String key, String format, int start, int perPage) {
		ArrayList bookByTypeList = null;
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

			bookByTypeList = (ArrayList) client.queryForList("getBookPageBySearchType", map, start, perPage);

			// System.out.println(map.toString()+"========");

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

	public ArrayList getBookByTypeList(String typeCode) {
		ArrayList bookByTypeList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			if (typeCode.endsWith("00000")) {
				typeCode = typeCode.substring(0, 5);
				bookByTypeList = (ArrayList) client.queryForList("getBookByFirstTypeList", typeCode);
			} else {
				bookByTypeList = (ArrayList) client.queryForList("getBookByTypeList", typeCode);
			}
			client.commitTransaction();
			client.endTransaction();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookByTypeList;

	}

	public Integer getBookByTypeCount(String typeCode) {
		Integer count = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			if (typeCode.endsWith("00000")) {
				typeCode = typeCode.substring(0, 5);
				count = (Integer) client.queryForObject("getBookByFirstTypeCount", typeCode);
			} else {
				count = (Integer) client.queryForObject("getBookByTypeCount", typeCode);
			}
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList getBookPageByType(String typeCode, int start, int toLen) {
		ArrayList list = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			if (typeCode.endsWith("00000")) {
				typeCode = typeCode.substring(0, 5);
				list = (ArrayList) client.queryForList("getBookPageByFirstType", typeCode, start, toLen);
			} else {
				list = (ArrayList) client.queryForList("getBookPageByType", typeCode, start, toLen);
			}

			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
