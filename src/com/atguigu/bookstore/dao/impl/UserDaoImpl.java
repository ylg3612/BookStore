package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User checkUserNameAndPassword(User user) {
		// 写sql语句
		String sql = "select id,username,password,email from users where username = ? and password = ?";
		User bean = getBean(sql, user.getUsername(), user.getPassword());
		return bean;
	}

	@Override
	public boolean checkUserName(User user) {
		// 写sql语句
		String sql = "select id,username,password,email from users where username = ?";
		User bean = getBean(sql, user.getUsername());
		return bean==null;
	}

	@Override
	public void saveUser(User user) {
		//写sql语句
		String sql = "insert into users(username,password,email) values(?,?,?)";
		update(sql, user.getUsername(),user.getPassword(),user.getEmail());
	}

}
