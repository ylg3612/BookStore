package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车类
 * @author HanYanBing
 *
 */
public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Map用来存放购物车中的购物项，key是图书的id，value是购物项对象
	private Map<String , CartItem> map = new LinkedHashMap<>();
	private int totalCount; //购物车中的图书的总数量，通过计算得到
	private double totalAmount; //购物车中图书的总金额，通过计算得到
	
	//清空购物车
	public void clearCart(){
		map.clear();
	}
	//更新购物项
	public void updateCartItem(String bookId , String bookCount){
		//根据图书的id获取对应的购物项
		CartItem cartItem = map.get(bookId);
		try {
			int parseInt = Integer.parseInt(bookCount);
			if(parseInt <=  0){
				parseInt = cartItem.getCount();
			}
			//重新设置购物项中图书的数量
			cartItem.setCount(parseInt);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
	//删除购物项
	public void deleteCartItem(String bookId){
		map.remove(bookId);
	}
	//将图书添加到购物车中
	public void addBook2Cart(Book book){
		//根据图书的id获取购物车中对应的购物项
		CartItem cartItem = map.get(book.getId()+"");
		if(cartItem == null){
			//说明现在买的这本图书购物车中还没有对应的购物项
			cartItem = new CartItem();
			//将图书添加到购物项中
			cartItem.setBook(book);
			//设置该购物项中图书的数量为1
			cartItem.setCount(1);
			//将购物项添加到购物车的map中
			map.put(book.getId()+"", cartItem);
		}else{
			//说明购物车中已经有该图书对应的购物项，这时我们只需要更改购物项中图书的数量
			cartItem.setCount(cartItem.getCount()+1);
		}
	}
	
	//获取购物车中所有的购物项
	public List<CartItem> getCartItems(){
		Collection<CartItem> values = map.values();
		return new ArrayList<CartItem>(values);
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	//由购物项中的图书的数量相加得到
	public int getTotalCount() {
		int totalCount = 0;
		//获取所有的购物项
		List<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
			totalCount += cartItem.getCount();
		}
		return totalCount;
	}
//	public void setTotalCount(int totalCount) {
//		this.totalCount = totalCount;
//	}
	//由购物项中的图书的金额小计相加得到
	public double getTotalAmount() {
//		double totalAmount = 0;
		BigDecimal bigTotalAmount = new BigDecimal("0");
		//获取所有的购物项
		List<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
//			totalAmount += cartItem.getAmount();
			BigDecimal bigAmount = new BigDecimal(cartItem.getAmount()+"");
			bigTotalAmount = bigTotalAmount.add(bigAmount);
		}
		return bigTotalAmount.doubleValue();
	}
//	public void setTotalAmount(double totalAmount) {
//		this.totalAmount = totalAmount;
//	}
	
	
}
