package com.atguigu.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WEBUtils;

/**
 * 前台处理图书的Servlet
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

	// 获取带价格范围和分页的图书信息
	protected void getPageBooksByPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取页码和价格范围
		String pageNo = request.getParameter("pageNo");
		String minPrice = request.getParameter("min");
		String maxPrice = request.getParameter("max");
		// 获取请求路径
		String path = WEBUtils.getPath(request);
		// 调用bookService的方法获取带价格范围和分页的图书信息
		Page<Book> pageBooksByPrice = bookService.getPageBooksByPrice(pageNo, minPrice, maxPrice);
		// 将path设置到pageBooksByPrice对象中
		pageBooksByPrice.setPath(path);
		// 将pageBooksByPrice对象放到request域中
		request.setAttribute("page", pageBooksByPrice);
		// 转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);
	}

	// 获取带分页的图书信息
	protected void getPageBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户传入的页码
		String pageNo = request.getParameter("pageNo");
		// 获取请求路径
		String path = WEBUtils.getPath(request);
		// 调用bookService的方法获取带分页的图书
		Page<Book> pageBooks = bookService.getPageBooks(pageNo);
		// 将path设置到pageBooks对象中
		pageBooks.setPath(path);
		// 将pageBooks放到request域中
		request.setAttribute("page", pageBooks);
		// 转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/client/books.jsp").forward(request, response);
	}

}
