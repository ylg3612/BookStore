package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 专门用来被继承的一个Servlet
 * 
 * @author HanYanBing
 *
 */
public class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
		// 获取method
		String methodName = request.getParameter("method");
		// if ("login".equals(method)) {
		// // 证明在登录
		// login(request, response);
		// } else if ("regist".equals(method)) {
		// // 证明在注册
		// regist(request, response);
		// }
		try {
			// 1.获取方法对象
			// getDeclaredMethod()方法中需要两个参数
			// 第一个参数是要调用的方法的方法名；第二个参数是要调用的方法的参数的类型
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			// 2.设置访问权限
			method.setAccessible(true);
			// 3.调用方法
			// invoke()方法也需要传入两个参数
			// 第一个参数是要调用那个对象的方法；第二个参数是要调用的方法中需要传入的参数
			method.invoke(this, request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			//将编译时异常转换为运行时异常向上抛
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
