/*
 * �๦�ܣ�ʵ�������ݿ����ز�������������������������ر�����
 * �����ˣ�������
 * ����ʱ�䣺2020��3��14��
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
	
	/*���������봴������*/
	public static Connection getConnection() throws SQLException {
		//��������
		try {
			Class.forName(driver);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//��������
		Connection con = DriverManager.getConnection(url,user,password);
		return con;
	}
}
