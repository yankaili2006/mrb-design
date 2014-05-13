package com.mrb.ibatis;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;

import com.ibatis.common.resources.Resources;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * Feb 25, 2011 
 * SqlMap.java 
 */

/**
 * @author Administrator 12:07:57 PM
 */
public class SqlMap {

	private static SqlMapClient sqlMap = null;

	public static SqlMapClient getSqlMapInstance() {

		if (sqlMap == null) {

			synchronized (SqlMap.class) {
				if (sqlMap == null) {
					try {
						String resource = "com/mrb/config/SqlMapConfig.xml";
						
						Reader reader = Resources.getResourceAsReader(resource);
						
						sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
						
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Error initializing MyAppSqlConfig class. Cause: " + e);
					}

				}
			}

		}

		return sqlMap;
	}

}
