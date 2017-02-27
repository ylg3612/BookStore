package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;

public interface OrderService {

	/**
	 * 去结账的方法
	 * 
	 * @return
	 */
	public String createOrder(User user, Cart cart);

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
	 * 根据订单号获取所有订单项
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> getOrderItems(String orderId);

	/**
	 * 根据订单号更新订单的状态
	 * 
	 * @param orderId
	 * @param state
	 */
	public void updateOrderState(String orderId, int state);
}
