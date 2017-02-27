package com.atguigu.bookstore.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;

public class CartTest {

	@Test
	public void test() {
		//创建两本图书
		Book book1 = new Book(1, "母猪的产后护理", "公猪", 0.01, 100, 100);
		Book book2 = new Book(2, "母猪很幸福", "母猪", 0.06, 100, 100);
		
		//创建购物车
		Cart cart = new Cart();
		//将两本图书添加到购物车中
		cart.addBook2Cart(book1);
		cart.addBook2Cart(book2);
		
//		//再买一本book1
//		cart.addBook2Cart(book1);
//		
//		//更新book2的数量
//		cart.updateCartItem("2", "10");
//		
//		//删除book1
//		cart.deleteCartItem("1");
//		
//		//清空购物车
//		cart.clearCart();
		
		System.out.println("购物车中图书的总数量为："+cart.getTotalCount());
		System.out.println("购物车中图书的总金额为："+cart.getTotalAmount());
	}

}
