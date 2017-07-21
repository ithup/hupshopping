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

}
