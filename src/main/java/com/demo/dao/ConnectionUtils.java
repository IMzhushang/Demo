package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * jdbc连接
 * 
 * @author Administrator
 *
 */
public class ConnectionUtils {
	private static final String url = "jdbc:mysql://localhost:3306/test";
	private static final String username = "root";
	private static final String password = "1234";

	private static Connection con = null;

	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection geConnection() {
		return con;
	}

	public static void close() {

	}

}
