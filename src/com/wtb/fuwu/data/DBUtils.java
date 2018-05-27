package com.wtb.fuwu.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
	private String url;
	private String username;
	private String password;
	private static DBUtils db;
	private static Object obj = new Object();

	public static DBUtils getInstance() {
		try {
			synchronized (obj) {
				if (db == null) {
					Class.forName("com.mysql.jdbc.Driver");
					db = new DBUtils();
					db.url = "jdbc:mysql://localhost:3306/yunying?characterEncoding=utf8&useSSL=true";
					db.username = "root";
					db.password = "pachira";
				}
			}
		} catch (Exception e) {
			db = null;
			e.printStackTrace();
		}
		return db;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.url, this.username, this.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void setAutoCommit(Connection conn,boolean autoCommit) {
		try {
			if(conn != null) {
				conn.setAutoCommit(autoCommit);
			}
		} catch (Exception e) {
			System.err.println("设置数据库默认提交["+autoCommit+"]失败："+e.getMessage());
		}
	}
	
	public static void rollback(Connection conn) {
		if(conn != null) {
			try {
				conn.rollback();
			} catch (Exception e) {
				System.err.println("数据库回滚失败");
			}
		}
	}
	
}
