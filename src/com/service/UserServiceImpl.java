/*
 * �๦�ܣ�ʵ��ҵ���߼���Ľӿڣ������Ƿ�ע��ɹ�
 * �����ˣ�������
 * ����ʱ�䣺2020��3��18��
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
	 * ��service�����dao��Ľӿڣ������ݿ��reader���deposit���в�������
	 * ��re_id�������˻�Ϊ�ջ��ʽ���������ʽҲ��ҳ�������жϣ������ʽ���ᴫ���ˣ����򷵻� -1����ʾ�������
	 * ����������Ϣ��ȷ��insert into ���ݿ��У������˻���ע�ᣬ�򷵻�0�����ɹ�ע�ᣬ�򷵻�1
	 */
	@Override
	public int registReAndDeposit(String re_id, String re_email) {
		
		//�ж�re_id�Ƿ�Ϊ��
		if(re_id == null) {
			logger.error("The reader's id con't be null.");
			return -1;
		}
		
		//�ж�re_id��ʽ�Ƿ���ȷ
		Pattern p = Pattern.compile("^1[3456789]\\d{9}$");	//�绰���������ʽ
		boolean b = p.matcher(re_id).matches();	//�ж϶����˺�re_id�Ƿ���ϵ绰���������ʽ
		if(!b) {
			logger.error("The format of this reader's id is not correct.");
			return -1;	//������id�����ϵ绰����ĸ�ʽ���򷵻� -1���ҶԷ���ֵ��������
		}
		
		
		//�����˺�������ȷ  �����ʽ��ȷ
		int ifRegistSuc = user.addReader(re_id, re_email);//�Ƿ�ע��ɹ����ɹ�����1�����ɹ�����0
		if( ifRegistSuc != 0) { //ע��ɹ�
			user.addDeposit(re_id); //���ɱ�֤��
		}
		return ifRegistSuc;
	}
}
