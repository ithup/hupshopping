package com.ithuplion.dao;


import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
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

}
