package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

/**
 * 操作购物车的Servlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

	// 更新购物项的方法
	protected void updateCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id和用户输入的数量
		String bookId = request.getParameter("bookId");
		String bookCount = request.getParameter("bookCount");
		// 获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.updateCartItem(bookId, bookCount);
		}
		//获取购物车中图书的总数量
		int totalCount = cart.getTotalCount();
		//获取购物车中图书的总金额
		double totalAmount = cart.getTotalAmount();
		//获取购物项中图书的金额小计
		CartItem cartItem = cart.getMap().get(bookId);
		double amount = cartItem.getAmount();
		//创建一个Map对象
		Map<String , Object> map = new HashMap<>();
		map.put("totalCount", totalCount+"");
		map.put("totalAmount", totalAmount+"");
		map.put("amount", amount+"");
		//创建Gson对象
		Gson gson = new Gson();
		//将Map转换为JSON字符串
		String json = gson.toJson(map);
		System.out.println(json);
		//将JSON字符串响应到浏览器
		response.getWriter().write(json);
		// 重定向到购物车页面
//		response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
		
		
		
		

	}

	// 清空购物车的方法
	protected void clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.clearCart();
		}
		// 重定向到购物车页面
		response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
	}

	// 删除购物项的方法
	protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 获取session域中的购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.deleteCartItem(bookId);
		}
		// 重定向到购物车页面
		response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
	}

	// 添加图书的方法
	protected void addBook2Cart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 调用bookService的方法获取图书的信息
		Book bookById = bookService.getBookById(bookId);
		// 从session域中获取购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			// 创建一个购物车
			cart = new Cart();
			// 将购物车添加到session域中
			session.setAttribute("cart", cart);
		}
		// 将图书添加到购物车中
		cart.addBook2Cart(bookById);
//		//获取购物车中该购物项
		Map<String, CartItem> map = cart.getMap();
		CartItem cartItem = map.get(bookId);
		//获取购物项中图书的数量
		int count = cartItem.getCount();
		//获取该图书的库存
		Integer stock = bookById.getStock();
		//比较购物项中图书的数量和库存的大小
		if(count > stock){
			//已经超库存，设置一个错误消息
			session.setAttribute("msg", "该图书的库存只有"+stock+"本！");
			//将购物项中图书的数量设置为库存的数量
			cartItem.setCount(stock);
		}
		// 将图书的书名放到session域中
		session.setAttribute("bookTitle", bookById.getTitle());
		// 获取请求从哪儿来
		String header = request.getHeader("Referer");
		// 重定向到header
		response.sendRedirect(header);
	}

}
