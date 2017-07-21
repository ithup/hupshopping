package com.ithuplion.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithuplion.service.UserService;

public class ActiveServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得激活码
		String activeCode = request.getParameter("activeCode");
		UserService userService=new UserService();
		userService.active(activeCode);
		//跳转到登录页面
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

}
