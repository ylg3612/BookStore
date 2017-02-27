package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 前台操作订单的Servlet
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private OrderService orderService = new OrderServiceImpl();

	//确认收货
	protected void getOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取订单号
		String orderId = request.getParameter("orderId");
		//调用orderService的方法更新订单状态
		orderService.updateOrderState(orderId, 2);
		//调用getMyOrders方法重新获取一下我的订单
		getMyOrders(request, response);
	}
	// 获取我的订单
	protected void getMyOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取session域中的用户
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 获取用户的id
		Integer userId = user.getId();
		// 调用orderService的方法获取用户的所有订单
		List<Order> myOrders = orderService.getMyOrders(userId);
		// 将myOrders放到request域中
		request.setAttribute("orders", myOrders);
		// 转发到显示所有订单的页面
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
	}

	// 去结账
	protected void checkout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取Session对象
		HttpSession session = request.getSession();
		// 获取session中的用户
		User user = (User) session.getAttribute("user");
		// 获取session域中的购物车
		Cart cart = (Cart) session.getAttribute("cart");
		// 调用orderService的方法获取生成订单号
		String createOrder = orderService.createOrder(user, cart);
		// 将订单号放到session域中
		session.setAttribute("orderId", createOrder);
		// 重定向到显示订单号的页面
		response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
	}

}
