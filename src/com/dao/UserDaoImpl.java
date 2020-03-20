/*
 * 类功能：向数据库的表中添加读者账号信息与保证金信息
 * 创建人：雷沛钶  周雨婷
 * 创建时间：2020年3月15日
 */

package com.dao;

import com.util.JdbcUtil;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class UserDaoImpl implements UserDao {
	private Connection con = null;
	private int count = 0;
    String de_librarian = null; //当前正在工作的图管id
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
	
	
	/*
	 * 将新注册的读者账户信息添加到reader表中
	 */
	@Override
	public int addReader(String re_id,String re_email) {

		try{
			con = JdbcUtil.getConnection();
			logger.debug("Database gets connection successfully.");
			
			//查询当前在工作的图管的id
			Statement stmt  = con.createStatement();
            String sql ="select * from librarian where li_state=1 ;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                de_librarian = rs.getString("li_id");
            }
            rs.close();
            stmt.close();
			
			//查询该账户是否已经存在
			String sql1 = "select * from reader where re_id=?";
			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst1.setString(1, re_id);
			ResultSet rs1 = pst1.executeQuery();
			if(rs1.next()) {
				logger.info("[LIBRARIAN]ID:" + de_librarian + "   " + "Regist the reader '" + re_id +"' fail. This reader's id is already exists.");
				return 0;//若已存在则返回0
			}
			rs1.close();
			pst1.close();
			
			//查询该邮箱是否已经被注册
			String sql2 = "select * from reader where re_email=?";
			PreparedStatement pst2 = con.prepareStatement(sql2);
			pst2.setString(1, re_email);
			ResultSet rs2 = pst2.executeQuery();
			if(rs2.next()) {
				logger.info("[LIBRARIAN]ID:" + de_librarian + "   " + "Regist the reader '" + re_id +"' fail. This reader's email is already exists.");
				return 0;//若已存在则返回0
			}
			rs2.close();
			pst2.close();
			
			//若不存在则将读者信息加入数据库
			String default_password = "12345678";
			String sql3 = "insert into Reader values(?,?,?,1,0,0,0,0)";
			PreparedStatement pst3 = con.prepareStatement(sql3);
			pst3.setString(1, re_id);
			pst3.setString(2, default_password);
			pst3.setString(3, re_email);
			count = pst3.executeUpdate();
			if (count != 0){
				logger.info("[LIBRARIAN]ID:" + de_librarian + "   " + "Regist the reader '" + re_id +"' succeed. ");
			}
			pst3.close();
		} catch(SQLException e){
			e.printStackTrace();
			logger.debug("Database gets connection falsely.");
		} finally {
			if(con != null) {
				try {
					con.close();
					logger.debug("Database closes connection successfully.");
				} catch (SQLException e) {
					e.printStackTrace();
					logger.debug("Database closes connection falsely.");
				}
			}
		}
		return count;
	}

	/*
	 * 将读者缴纳的保证金相关信息添加到deposit表中
	 */
	@Override
	public int addDeposit(String de_reader) {
		int de_amount=0;
        Statement stmt = null;

        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String de_time = formatter.format(calendar.getTime());

        try {
        	con = JdbcUtil.getConnection();
        	logger.debug("Database gets connection successfully.");
        	
            //查询当前设定的保证金金额
            stmt  = con.createStatement();
            String sql1 ="select * from systems;";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while(rs1.next()) {
                de_amount = rs1.getInt("sys_deposit");
            }
            rs1.close();
            
            //将保证金记录插入表Deposit
            String sql2 = "insert into deposit(de_reader,de_librarian,de_amount,de_time) values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql2);
			pst.setString(1, de_reader);
			pst.setString(2, de_librarian);
			pst.setInt(3, de_amount);
			pst.setString(4, de_time);
			count = pst.executeUpdate();
			if (count != 0){
				logger.info("[LIBRARIAN]ID:" + de_librarian + "   " +"The reader '" + de_reader +"' pay the deposit succeed.");
			}
			pst.close();
			
        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug("Database gets connection falsely.");
        }finally {
            if (con != null) {
                try {
                	con.close();
					logger.debug("Database closes connection successfully.");
				} catch (SQLException e) {
					e.printStackTrace();
					logger.debug("Database closes connection falsely.");
				}
            }
        }
		return count;
	}
}