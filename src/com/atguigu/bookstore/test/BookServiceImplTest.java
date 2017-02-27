package com.atguigu.bookstore.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

public class BookServiceImplTest {

	BookService bookService = new BookServiceImpl();
	@Test
	public void test() {
		Page<Book> pageBooks = bookService.getPageBooks("abc");
		List<Book> list = pageBooks.getList();
		for (Book book : list) {
			System.out.println(book);
		}
	}

}
