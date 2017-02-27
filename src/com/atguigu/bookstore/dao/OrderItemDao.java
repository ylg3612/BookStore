package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;

public interface OrderItemDao {

	/**
	 * 向数据库中插入订单项的方法
	 * 
	 * @param orderItem
	 */
	public void saveOrderItem(OrderItem orderItem);

	/**
	 * 根据订单号获取对应的订单项
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> getOrerItems(String orderId);

	/**
	 * 批量插入订单项
	 * 
	 * @param params
	 */
	public void batchOrderItems(Object[][] params);
}
