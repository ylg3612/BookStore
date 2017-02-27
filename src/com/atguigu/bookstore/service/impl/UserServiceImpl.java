package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();
	@Override
	public User login(User user) {
		return userDao.checkUserNameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		boolean flag = userDao.checkUserName(user);
		return flag;
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

}
