package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车中的购物项类
 * @author HanYanBing
 *
 */
public class CartItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Book book; // 图书信息
	private int count; // 购物项中图书的数量
	private double amount; // 购物项中图书的金额小计，通过计算得到

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	//购物项中的金额小计由图书的价格和购物项中的图书的数量计算得到
	public double getAmount() {
		BigDecimal bigCount = new BigDecimal(count+"");
		BigDecimal bigPrice = new BigDecimal(book.getPrice()+"");
		return bigCount.multiply(bigPrice).doubleValue();
	}

//	public void setAmount(double amount) {
//		this.amount = amount;
//	}

}
