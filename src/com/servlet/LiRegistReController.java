/*
 * 类功能：control层，实现页面跳转 ，以及接收和返回前端数据
 * 创建人：雷沛钶
 * 创建时间：2020年3月18日
 */

package com.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.UserService;
import com.service.UserServiceImpl;

//Spring的注解；@Controller就表示这是一个servlet

@Controller
public class LiRegistReController {

	//浏览器的请求地址:/Mandarin/AddR.htm
	@RequestMapping("AddR")
	public ModelAndView liRegistRe(HttpServletRequest req,HttpServletResponse resp) {
		
		ModelAndView mv = new ModelAndView("AddR");//跳转到AddR.html这个页面

		String re_id = req.getParameter("Account");//获取读者账号
		String re_email = req.getParameter("Email");//获取读者账号
		System.out.println("id = "+ re_id +"Email = " + re_email);
		
		UserService li = new UserServiceImpl();
		int ifRegistSuc = li.registReAndDeposit(re_id, re_email);//ifRegistSuc 表示是否注册成功以及输入信息是否有错
		
		System.out.println("是否注册成功： " + ifRegistSuc + "  (0表示注册失败 该账号id或Email已存在；1表示注册成功；-1表示信息有误)");
		
		mv.addObject("ifRegistSuc", ifRegistSuc);//将该ifRegistSuc值传给前端
		
		return mv;
	}
	
}
