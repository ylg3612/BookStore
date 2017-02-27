package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;

public interface OrderDao {

	/**
	 * 向数据库中插入订单的方法
	 * 
	 * @param order
	 */
	public void saveOrder(Order order);

	/**
	 * 获取所有订单
	 * 
	 * @return
	 */
	public List<Order> getOrders();

	/**
	 * 根据用户的id获取用户的所有订单
	 * 
	 * @return
	 */
	public List<Order> getMyOrders(int userId);

	/**
	 * 根据订单号更新订单的状态
	 * 
	 * @param orderId
	 * @param state
	 */
	public void updateOrderState(String orderId, int state);
}
