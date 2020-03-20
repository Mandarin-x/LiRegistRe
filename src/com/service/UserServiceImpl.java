/*
 * 类功能：实现业务逻辑层的接口，返回是否注册成功
 * 创建人：雷沛钶
 * 创建时间：2020年3月18日
 */

package com.service;

import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.dao.UserDaoImpl;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao user = new UserDaoImpl();
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	

	/*
	 * 在service层调用dao层的接口，向数据库的reader表和deposit表中插入数据
	 * 若re_id即读者账户为空或格式错误（邮箱格式也在页面做出判断，错误格式不会传入后端），则返回 -1，表示输入错误
	 * 否则，输入信息正确，insert into 数据库中，若该账户已注册，则返回0；若成功注册，则返回1
	 */
	@Override
	public int registReAndDeposit(String re_id, String re_email) {
		
		//判断re_id是否为空
		if(re_id == null) {
			logger.error("The reader's id con't be null.");
			return -1;
		}
		
		//判断re_id格式是否正确
		Pattern p = Pattern.compile("^1[3456789]\\d{9}$");	//电话号码的正则式
		boolean b = p.matcher(re_id).matches();	//判断读者账号re_id是否符合电话号码的正则式
		if(!b) {
			logger.error("The format of this reader's id is not correct.");
			return -1;	//若读者id不符合电话号码的格式，则返回 -1，且对返回值不做处理
		}
		
		
		//读者账号输入正确  邮箱格式正确
		int ifRegistSuc = user.addReader(re_id, re_email);//是否注册成功；成功返回1，不成功返回0
		if( ifRegistSuc != 0) { //注册成功
			user.addDeposit(re_id); //收纳保证金
		}
		return ifRegistSuc;
	}
}
