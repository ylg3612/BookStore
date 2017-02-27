package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

public interface UserDao {

	/**
	 * 根据传入的User对象的用户名和密码从数据库中查询出对应的记录
	 * 
	 * @param user
	 * @return User 数据库中有该记录 null 数据库中为该记录
	 */
	public User checkUserNameAndPassword(User user);

	/**
	 * 根据传入的User对象的用户名从数据库中查询出是否有对应的记录
	 * 
	 * @param user
	 * @return true 没有对应的记录 false 有对应的记录
	 */
	public boolean checkUserName(User user);

	/**
	 * 向数据库中保存用户
	 * 
	 * @param user
	 */
	public void saveUser(User user);
}
