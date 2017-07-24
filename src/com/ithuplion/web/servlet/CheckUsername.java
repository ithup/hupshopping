package com.ithuplion.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithuplion.service.UserService;

public class CheckUsername extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得用户名
		String username = request.getParameter("username");
		// 将username传递给服务层
		UserService userService = new UserService();
		boolean isExist = userService.checkUsername(username);
		String json = "{\"isExist\":" + isExist + "}";
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
