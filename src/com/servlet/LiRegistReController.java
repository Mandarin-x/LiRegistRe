/*
 * �๦�ܣ�control�㣬ʵ��ҳ����ת ���Լ����պͷ���ǰ������
 * �����ˣ�������
 * ����ʱ�䣺2020��3��18��
 */

package com.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.UserService;
import com.service.UserServiceImpl;

//Spring��ע�⣻@Controller�ͱ�ʾ����һ��servlet

@Controller
public class LiRegistReController {

	//������������ַ:/Mandarin/AddR.htm
	@RequestMapping("AddR")
	public ModelAndView liRegistRe(HttpServletRequest req,HttpServletResponse resp) {
		
		ModelAndView mv = new ModelAndView("AddR");//��ת��AddR.html���ҳ��

		String re_id = req.getParameter("Account");//��ȡ�����˺�
		String re_email = req.getParameter("Email");//��ȡ�����˺�
		System.out.println("id = "+ re_id +"Email = " + re_email);
		
		UserService li = new UserServiceImpl();
		int ifRegistSuc = li.registReAndDeposit(re_id, re_email);//ifRegistSuc ��ʾ�Ƿ�ע��ɹ��Լ�������Ϣ�Ƿ��д�
		
		System.out.println("�Ƿ�ע��ɹ��� " + ifRegistSuc + "  (0��ʾע��ʧ�� ���˺�id��Email�Ѵ��ڣ�1��ʾע��ɹ���-1��ʾ��Ϣ����)");
		
		mv.addObject("ifRegistSuc", ifRegistSuc);//����ifRegistSucֵ����ǰ��
		
		return mv;
	}
	
}
