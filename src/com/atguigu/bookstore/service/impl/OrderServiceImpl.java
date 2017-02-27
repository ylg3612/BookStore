package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDao orderDao = new OrderDaoImpl();
	OrderItemDao orderItemDao = new OrderItemDaoImpl();
	BookDao bookDao = new BookDaoImpl();
	@Override
	public String createOrder(User user , Cart cart) {
		//获取用户的id
		Integer id = user.getId();
		// 生成订单号
		String orderId = System.currentTimeMillis()+""+id;
		//获取购物车中的总金额
		double totalAmount = cart.getTotalAmount();
		//获取购物车中图书的总数量
		int totalCount = cart.getTotalCount();
		//封装Order对象
		Order order = new Order(orderId, new Date(), totalCount, totalAmount, 0, id);
		//将订单插入到数据库
		orderDao.saveOrder(order);
		
		//获取购物车中所有的购物项
		List<CartItem> cartItems = cart.getCartItems();
		
		//创建两个二维数组
		Object[][] orderItemParams = new Object[cartItems.size()][];
		Object[][] bookParams = new Object[cartItems.size()][];
		
		for(int i = 0 ; i < cartItems.size() ; i++){
			//获取每一个购物项
			CartItem cartItem = cartItems.get(i);
			//获取购物项中的图书的数量
			int count = cartItem.getCount();
			//获取购物车中的金额小计
			double amount = cartItem.getAmount();
			//获取购物项中的图书
			Book book = cartItem.getBook();
			//获取图书的名字
			String title = book.getTitle();
			//获取图书的作者
			String author = book.getAuthor();
			//获取图书的价格
			double price = book.getPrice();
			//获取图书的封面
			String imgPath = book.getImgPath();
			//封装OrderItem对象
//			OrderItem orderItem = new OrderItem(null, count, amount, title, author, price, imgPath, orderId);
			//将订单项插入到数据库中
//			orderItemDao.saveOrderItem(orderItem);
			//填充插入订单项时的占位符
			//(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)
			orderItemParams[i] = new Object[]{count, amount, title, author, price, imgPath, orderId};
			
			//更新图书的库存和销量
			//获取图书的库存和销量
			Integer sales = book.getSales();
			Integer stock = book.getStock();
//			book.setSales(sales + count);
//			book.setStock(stock - count);
			//更新图书信息
//			bookDao.updateBook(book);
			//填充更新图书的库存和销量的占位符
			//update books set sales = ? , stock = ? where id = ?
			//判断购买的数量与库存的大小
			if(stock - count < 0){
				//抛一个运行时异常
				throw new RuntimeException("库存不足！");
			}
			bookParams[i] = new Object[]{sales + count , stock - count , book.getId() };
		}
		//批量插入订单项
		orderItemDao.batchOrderItems(orderItemParams);
		//批量更新图书的库存和销量
		bookDao.batchUpdateSalesAndStock(bookParams);
		//生成订单之后将购物车清空
		cart.clearCart();
		return orderId;
	}
	@Override
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}
	@Override
	public List<Order> getMyOrders(int userId) {
		return orderDao.getMyOrders(userId);
	}
	@Override
	public List<OrderItem> getOrderItems(String orderId) {
		return orderItemDao.getOrerItems(orderId);
	}
	@Override
	public void updateOrderState(String orderId, int state) {
		orderDao.updateOrderState(orderId, state);
	}

}
