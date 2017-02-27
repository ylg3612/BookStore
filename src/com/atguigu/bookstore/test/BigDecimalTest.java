package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalTest {

	@Test
	public void testInt() {
		BigDecimal a = new BigDecimal(10);
		BigDecimal b = new BigDecimal(5);
		//加
		BigDecimal add = a.add(b);
		System.out.println(add);
		//减
		BigDecimal subtract = a.subtract(b);
		System.out.println(subtract);
		//乘
		BigDecimal multiply = a.multiply(b);
		System.out.println(multiply);
		//除
		BigDecimal divide = a.divide(b);
		System.out.println(divide);
	}
	
	@Test
	public void testDouble(){
		//使用BigDecimal的String的构造器解决double类型的计算精度问题
		BigDecimal a = new BigDecimal("0.01");
		BigDecimal b = new BigDecimal("0.09");
		BigDecimal add = a.add(b);
		System.out.println(add.doubleValue());
	}

}
