package com.ithuplion.dao;


import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ithuplion.domain.User;
import com.ithuplion.utils.DataSourceUtils;

/**
 * 用户数据层
 * @author acer
 *
 */
public class UserDao {
	private QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
	public int register(User user) {
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int update = 0;
		try {
			update=qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),user.getName(),
					user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}
	/**
	 * 用户数据层：用户登录
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public User login(User user) throws Exception {
		String sql="select * from user where username=? and password=?";
		return qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}
	/**
	 * 激活验证码
	 * @param activeCode
	 * @throws Exception 
	 */
	public void active(String activeCode) throws Exception {
		String sql = "update user set state=? where code=?";
		qr.update(sql, 1,activeCode);
	}

}
