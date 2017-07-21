package com.ithuplion.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ithuplion.domain.User;
import com.ithuplion.service.UserService;
import com.ithuplion.utils.CommonsUtils;

public class Register extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理请求编码
		request.setCharacterEncoding("UTF-8");
		//获得表单数据
		Map<String,String[]> parameterMap = request.getParameterMap();
		User user=new User();
		try {
			
			BeanUtils.populate(user, parameterMap);//映射封装
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		user.setUid(CommonsUtils.getUUID());
		user.setTelephone(null);
		user.setState(0);//是否激活
//		user.setBirthday(request.getParameter("birthday"));
		user.setCode(CommonsUtils.getUUID());//激活码
		//将user传递给Service层
		UserService userService=new UserService();
		boolean isRegisterSuccess=userService.register(user);
		if(isRegisterSuccess){
			System.out.println("success");
		}else{
			System.out.println("error");
		}
	}

}
