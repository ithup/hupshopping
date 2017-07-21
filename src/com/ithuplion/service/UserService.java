package com.ithuplion.service;

import com.ithuplion.dao.UserDao;
import com.ithuplion.domain.User;

/**
 * userҵ���
 * @author acer
 *
 */
public class UserService {
	private UserDao userDao=new UserDao();
	/**
	 * �û�ע�Ṧ��
	 * @param user
	 * @return
	 */
	public boolean register(User user) {
		int row=userDao.register(user);
		return row>0?true:false;
	}
	/**
	 * �û���¼����
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public User login(User user) throws Exception {
		return userDao.login(user);
	}
	/**
	 * ������֤��
	 * @param activeCode
	 */
	public void active(String activeCode) {
		try {
			userDao.active(activeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
