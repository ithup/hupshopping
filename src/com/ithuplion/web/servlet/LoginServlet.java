package com.ithuplion.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithuplion.domain.User;
import com.ithuplion.service.UserService;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		//½«user´«µÝ¸øService²ã
		UserService userService=new UserService();
		try {
			User userLoginSuccess=userService.login(user);
			if(userLoginSuccess!=null){
				response.sendRedirect(request.getContextPath()+"/loginSuccess.jsp");
			}else{
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
