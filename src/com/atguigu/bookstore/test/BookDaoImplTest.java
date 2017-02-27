package com.atguigu.bookstore.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class BookDaoImplTest {

	BookDao bookDao = new BookDaoImpl();
	@Test
	public void testGetBooks() {
		List<Book> books = bookDao.getBooks();
		for (Book book : books) {
			System.out.println(book);
		}
	}

	@Test
	public void testGetPageBooks(){
		//创建Page对象
		Page<Book> page1  = new Page<>();
		page1.setPageNo(1);
		Page<Book> page = bookDao.getPageBooks(page1);
		//获取page对象中的总记录数
		int totalRecord = page.getTotalRecord();
		//获取总页数
		int totalPageNo = page.getTotalPageNo();
		//获取集合
		List<Book> list = page.getList();
		System.out.println("总记录数是："+totalRecord);
		System.out.println("总页数是："+totalPageNo);
		for (Book book : list) {
			System.out.println(book);
		}
	}
	@Test
	public void testGetPageBooksByPrice(){
		//创建Page对象
		Page<Book> page1  = new Page<>();
		page1.setPageNo(7);
		Page<Book> page = bookDao.getPageBooksByPrice(page1, 10, 30);
		//获取page对象中的总记录数
		int totalRecord = page.getTotalRecord();
		//获取总页数
		int totalPageNo = page.getTotalPageNo();
		//获取集合
		List<Book> list = page.getList();
		System.out.println("总记录数是："+totalRecord);
		System.out.println("总页数是："+totalPageNo);
		for (Book book : list) {
			System.out.println(book);
		}
	}
	
	@Test
	public void testBatch(){
		Object[][] params = new Object[3][];
		//update books set sales = ? , stock = ? where id = ?
		params[0] = new Object[]{100,100,100};
		params[1] = new Object[]{200,200,101};
		params[2] = new Object[]{300,300,102};
		bookDao.batchUpdateSalesAndStock(params );
	}
}
