/*
 * 接口功能：实现对数据库表的insert操作
 * 创建人：雷沛钶  周雨婷
 * 创建时间：2020年3月15日
 */
package com.dao;

public interface UserDao {
	int addReader(String re_id,String re_email);//添加新注册的读者账户信息
	int addDeposit(String de_reader);//添加读者缴纳的保证金信息
}
