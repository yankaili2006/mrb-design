/**
 * Feb 25, 2011 
 * TestDriver.java 
 */
package com.mrb.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Administrator 12:58:39 PM
 * 
 * 测试数据库连接
 * 
 */
public class TestDriver {

	static final String DATABASE_URL = "jdbc:mysql://localhost:3309/mrb";

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(DATABASE_URL, "root", "admin");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM user");

			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++)
				System.out.println(metaData.getColumnName(i));
			System.out.println();

			while (resultSet.next()) {
				for (int i = 1; i <= numberOfColumns; i++)
					System.out.println(resultSet.getObject(i));
				System.out.println();
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

	}

}
