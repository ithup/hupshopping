package com.ithuplion.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.ithuplion.domain.User;
import com.ithuplion.service.UserService;
import com.ithuplion.utils.CommonsUtils;
import com.ithuplion.utils.MailUtils;

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
			//日期转换
			ConvertUtils.register(new Converter() {
				public Object convert(Class clazz, Object value) {
					//将string 转为Date
					SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					Date date=null;
					try {
						date = format.parse(value.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return date;
				}
			}, Date.class);
			BeanUtils.populate(user, parameterMap);//映射封装
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setUid(CommonsUtils.getUUID());
		user.setTelephone(null);
		user.setState(0);//是否激活
		//激活码
		String activeCode=CommonsUtils.getUUID();
		user.setCode(activeCode);
		//将user传递给Service层
		UserService userService=new UserService();
		boolean isRegisterSuccess=userService.register(user);
		if(isRegisterSuccess){
			String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
					+ "<a href='http://localhost:8080/hupshopping/active?activeCode="+activeCode+"'>"
							+ "http://localhost:8080/hupshopping/active?activeCode="+activeCode+"</a>";
			//发送激活邮件
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (Exception e) {
				System.out.println("网络异常，请连接网络");
			} 
			//跳转到注册成功页面
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else{
			//跳转到注册失败页面
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
	}

}
