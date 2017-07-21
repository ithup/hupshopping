package com.ithuplion.service;

import com.ithuplion.dao.UserDao;
import com.ithuplion.domain.User;

/**
 * user业务层
 * @author acer
 *
 */
public class UserService {
	private UserDao userDao=new UserDao();
	/**
	 * 用户注册功能
	 * @param user
	 * @return
	 */
	public boolean register(User user) {
		int row=userDao.register(user);
		return row>0?true:false;
	}
	/**
	 * 用户登录功能
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public User login(User user) throws Exception {
		return userDao.login(user);
	}
	/**
	 * 激活验证码
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
