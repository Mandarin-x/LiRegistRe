/*
 * 类功能：实现与数据库的相关操作，加载驱动、创建连接与关闭连接
 * 创建人：雷沛钶
 * 创建时间：2020年3月14日
 */

package com.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/mandarin?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";
	private static String user = "root";
	private static String password = "123456";
	
	/*加载驱动与创建连接*/
	public static Connection getConnection() throws SQLException {
		//加载驱动
		try {
			Class.forName(driver);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//创建连接
		Connection con = DriverManager.getConnection(url,user,password);
		return con;
	}
}
