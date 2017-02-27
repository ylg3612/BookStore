package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookDao {

	/**
	 * 获取所有的图书
	 * 
	 * @return
	 */
	public List<Book> getBooks();

	/**
	 * 向数据库中插入一本图书
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
	 * 根据图书的ID从数据库中获取图书的信息
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
	 * @return
	 */
	public Page<Book> getPageBooks(Page<Book> page);

	/**
	 * 获取带价格范围和带分页的图书信息
	 * 
	 * @param page
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public Page<Book> getPageBooksByPrice(Page<Book> page, double minPrice, double maxPrice);

	/**
	 * 批量更新图书的库存和销量
	 * 
	 * @param params
	 */
	public void batchUpdateSalesAndStock(Object[][] params);
}
