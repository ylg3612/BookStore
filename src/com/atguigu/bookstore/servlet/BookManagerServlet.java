package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

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
 * 后台管理图书的Servlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

	// 获取带分页的图书信息
	protected void getPageBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户传入的页码
		String pageNo = request.getParameter("pageNo");
		//获取请求路径
		String path = WEBUtils.getPath(request);
		// 调用bookService的方法获取带分页的图书
		Page<Book> pageBooks = bookService.getPageBooks(pageNo);
		//将path设置到pageBooks对象中
		pageBooks.setPath(path);
		// 将pageBooks放到request域中
		request.setAttribute("page", pageBooks);
		// 转发到显示所有图书的页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}

	// 更新或添加图书的方法
	protected void updateOrAddBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取修改之后的图书的新信息
		String bookId = request.getParameter("bookId");
		String title = request.getParameter("book_name");
		String author = request.getParameter("book_author");
		String price = request.getParameter("book_price");
		String sales = request.getParameter("book_sales");
		String stock = request.getParameter("book_stock");
		if ("".equals(bookId)) {
			// 证明在添加图书
			// 封装Book对象
			Book book = new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales),
					Integer.parseInt(stock));
			// 调用bookService的方法将图书插入到数据库中
			bookService.addBook(book);
		} else {
			// 证明在更新图书
			// 封装Book对象
			Book book = new Book(Integer.parseInt(bookId), title, author, Double.parseDouble(price),
					Integer.parseInt(sales), Integer.parseInt(stock));
			// 调用bookService的方法更新该图书信息
			bookService.updateBook(book);
		}
		// 方式二：重定向到BookManagerServlet中的getBooks方法
		response.sendRedirect(request.getContextPath() + "/BookManagerServlet?method=getPageBooks");
	}

	// 获取一本图书的信息
	protected void getBookById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 调用bookService的方法获取该图书的信息
		Book book = bookService.getBookById(bookId);
		// 将book放到request域中
		request.setAttribute("book", book);
		// 转发到修改图书信息的页面
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}

	// 删除图书的方法
	protected void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取图书的id
		String bookId = request.getParameter("bookId");
		// 调用bookService的方法删除该图书
		bookService.deleteBookById(bookId);
		// 方式二：重定向到BookManagerServlet中的getBooks方法
		response.sendRedirect(request.getContextPath() + "/BookManagerServlet?method=getPageBooks");
	}

	// 添加图书的方法
	// protected void addBook(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// // 获取图书的信息
	// String title = request.getParameter("book_name");
	// String author = request.getParameter("book_author");
	// String price = request.getParameter("book_price");
	// String sales = request.getParameter("book_sales");
	// String stock = request.getParameter("book_stock");
	// // 封装Book对象
	// Book book = new Book(null, title, author, Double.parseDouble(price),
	// Integer.parseInt(sales),
	// Integer.parseInt(stock));
	// // 调用bookService的方法将图书插入到数据库中
	// bookService.addBook(book);
	// // 方式一：调用getBooks方法重新从数据库中获取所有图书的信息
	// // getBooks(request, response);
	// // 方式二：重定向到BookManagerServlet中的getBooks方法
	// response.sendRedirect(request.getContextPath() +
	// "/BookManagerServlet?method=getBooks");
	// }

	// 获取所有图书的方法
//	protected void getBooks(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// 获取所有的图书
//		List<Book> books = bookService.getBooks();
//		// 将books放到request域中
//		request.setAttribute("books", books);
//		// 转发到显示所有图书的页面
//		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
//	}

}
