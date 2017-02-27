package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookService {

	/**
	 * 获取所有图书
	 * 
	 * @return
	 */
	public List<Book> getBooks();

	/**
	 * 添加图书的方法
	 * 
	 * @param book
	 */
	public void addBook(Book book);

	/**
	 * 根据图书的ID从数据库中删除图书
	 * 
	 * @param bookId
	 */
	public void deleteBookById(String bookId);

	/**
	 * 根据图书的ID获取该图书的信息
	 * 
	 * @param bookId
	 * @return
	 */
	public Book getBookById(String bookId);

	/**
	 * 根据图书的id更新图书的信息
	 * 
	 * @param book
	 */
	public void updateBook(Book book);

	/**
	 * 获取带分页的图书信息
	 * 
	 * @param pageNo
	 */
	public Page<Book> getPageBooks(String pageNo);
	/**
	 * 获取带价格范围和分页的图书信息
	 * 
	 * @param pageNo
	 */
	public Page<Book> getPageBooksByPrice(String pageNo , String minPrice , String maxPrice);
}
