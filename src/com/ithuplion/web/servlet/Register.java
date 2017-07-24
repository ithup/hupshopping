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
		//�����������
		request.setCharacterEncoding("UTF-8");
		//��ñ�����
		Map<String,String[]> parameterMap = request.getParameterMap();
		User user=new User();
		try {
			//����ת��
			ConvertUtils.register(new Converter() {
				public Object convert(Class clazz, Object value) {
					//��string תΪDate
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
			BeanUtils.populate(user, parameterMap);//ӳ���װ
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setUid(CommonsUtils.getUUID());
		user.setTelephone(null);
		user.setState(0);//�Ƿ񼤻�
		//������
		String activeCode=CommonsUtils.getUUID();
		user.setCode(activeCode);
		//��user���ݸ�Service��
		UserService userService=new UserService();
		boolean isRegisterSuccess=userService.register(user);
		if(isRegisterSuccess){
			String emailMsg = "��ϲ��ע��ɹ���������������ӽ��м����˻�"
					+ "<a href='http://localhost:8080/hupshopping/active?activeCode="+activeCode+"'>"
							+ "http://localhost:8080/hupshopping/active?activeCode="+activeCode+"</a>";
			//���ͼ����ʼ�
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (Exception e) {
				System.out.println("�����쳣������������");
			} 
			//��ת��ע��ɹ�ҳ��
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else{
			//��ת��ע��ʧ��ҳ��
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
	}

}
