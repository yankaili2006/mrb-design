/**
 * Feb 26, 2011 
 * BookTypeBS.java 
 */
package com.mrb.bs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 10:06:18 AM
 */
public class BookTypeBS {

	/**
	 * 
	 */
	public BookTypeBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 给定code 获取这个ID
	 * */
	public Integer getIdWithFullCode(String code){
		
		Integer id = null;
		
		SqlMapClient client = SqlMap.getSqlMapInstance();

		try {
			client.startTransaction();
			
			id = (Integer) client.queryForObject("getIdWithFullCode", code);

			//System.out.println(id+","+code);
			client.commitTransaction();
			client.endTransaction();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
	}
	
	
	/*
	 * 给定code 获取这个名称name
	 * */
	public String getNameWithFullCode(String code){
		
		String name = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			name = (String) client.queryForObject("getNameWithFullCode", code);
			//System.out.println(id+","+code);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
		
	}
	
	/*
	 * 给定code 获取这个目录名称
	 * */
	public String getPathWithCode(String code){
		
		String path = null;
		
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			if(code.length()==5){
				path = (String) client.queryForObject("getNameWithFullCode", code+"00000");
			}
			else if(code.length()==10){
				String pathB = (String) client.queryForObject("getNameWithFullCode", code);
				
				code = code.substring(0,5)+"00000";
				
				String pathA = (String) client.queryForObject("getNameWithFullCode", code);
				
				
				path = pathA+"\\"+pathB;
				
			}
			else{
				//code长度错误
			}
			//System.out.println(id+","+code);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
		
	}
	
	
	/*
	 * 获取一级分类列表
	 * */
	public ArrayList getTypeFirstList() {
		ArrayList typeFirstList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			typeFirstList = (ArrayList) client.queryForList("getTypeFirstList");
			//System.out.println(typeFirstList.size());
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typeFirstList;
	}
	
	
	/*
	 * 获取二级分类列表
	 * */
	public ArrayList getTypeSecondList() {
		ArrayList typeSecondList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			typeSecondList = (ArrayList) client.queryForList("getTypeSecondList");
			client.commitTransaction();
			client.endTransaction();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return typeSecondList;

	}
	
	/*
	 * 获取属于某一个一级分类的二级分级列表
	 * */
	public ArrayList getSecondBookTypeByFirst(String firstCode) {
		ArrayList typeSecondList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			typeSecondList = (ArrayList) client.queryForList("getSecondBookTypeByFirst", firstCode);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typeSecondList;

	}
	
	/*
	 * 根据分类名称，查询一级分类是否存在。
	 * */
	public Integer getFirstBookTypeCountByName(String typeName) {
		Integer count = null;
		
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			count = (Integer) client.queryForObject("getFirstBookTypeCountByName", typeName);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	/*
	 * 根据分类名称，查询二级分类是否存在。
	 * */
	public Integer getSecondBookTypeCountByFirstCodeSecondName(String firstTypeCode, String typeName) {
		Integer count = null;
		
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			Map map = new HashMap();
			map.put("firstTypeCode", firstTypeCode);
			map.put("typeName", typeName);
			count = (Integer) client.queryForObject("getSecondBookTypeCountByFirstCodeSecondName", map);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	
	/*
	 * 获取一级分类中的最大值。
	 * */
	public String getFirstBookTypeMax() {
		String max = null;
		
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			max = (String) client.queryForObject("getFirstBookTypeMax");
			if(max == null){
				max = "0";
			}
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return max;
	}

	/*
	 * 给定一级分类，获取二级分类中的最大值
	 * */
	public String getSecondBookTypeMaxByFirstCode(String firstTypeCode) {
		String max = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			max = (String) client.queryForObject("getSecondBookTypeMaxByFirstCode", firstTypeCode);
			if(max == null){
				max = "0";
			}
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return max;
	}
	
	
	/*
	 * 给定名称，获取一级分类的code。
	 * */
	public String getFirstBookTypeCodeByName(String typeName) {
		String code = null;
		
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			code = (String) client.queryForObject("getFirstBookTypeCodeByName", typeName);
			//System.out.println("getFirstBookTypeCodeByName:"+typeName);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	/*
	 * 给定名称，获取二级分类的code。
	 * */
	
	public String getSecondBookTypeByFirstCodeSecondName(String typeCode, String typeName) {
		String code = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			Map map = new HashMap();
			map.put("typeCode", typeCode);
			map.put("typeName", typeName);
			//System.out.println("=="+typeCode+","+typeName);
			code = (String) client.queryForObject("getSecondBookTypeByFirstCodeSecondName", map);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}
	
	
	public static void main(String[] args){
		BookTypeBS bs = new BookTypeBS();
		String path = bs.getPathWithCode("00001");
		System.out.println(path);
	}
	
}
