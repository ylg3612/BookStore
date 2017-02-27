package com.atguigu.bookstore.test;

import java.util.Date;

import org.junit.Test;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;

public class OrderDaoImplTest {

	OrderDao orderDao = new OrderDaoImpl();
	OrderItemDao orderItemDao = new OrderItemDaoImpl();
	@Test
	public void test() {
		//创建订单项
		OrderItem orderItem = new OrderItem(null, 2, 60.0, "牛魔王大战孙悟空", "红孩儿", 30.0, null, "13811131214");
		OrderItem orderItem2 = new OrderItem(null, 3, 150.0, "红孩儿火烧孙悟空", "牛魔王", 50.0, null, "13811131214");
		//创建订单
		Order order = new Order("13811131214", new Date(), 5, 210.0, 0, 1);
		//保存订单
		orderDao.saveOrder(order);
		//保存订单项
		orderItemDao.saveOrderItem(orderItem);
		orderItemDao.saveOrderItem(orderItem2);
	}

}
