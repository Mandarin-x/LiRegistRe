/*
 * �ӿڹ��ܣ�ʵ�ֶ����ݿ���insert����
 * �����ˣ�������  ������
 * ����ʱ�䣺2020��3��15��
 */
package com.dao;

public interface UserDao {
	int addReader(String re_id,String re_email);//�����ע��Ķ����˻���Ϣ
	int addDeposit(String de_reader);//��Ӷ��߽��ɵı�֤����Ϣ
}
