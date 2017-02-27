package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

/**
 * 处理用户登录注册的Servlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();

	//使用Ajax验证用户名是否可用的方法
	protected void checkUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户名
		String userName = request.getParameter("username");
		//封装User对象
		User user = new User(null, userName, null, null);
		//调用userService的方法验证用户名是否可用
		boolean regist = userService.regist(user);
		response.setContentType("text/html;charset=UTF-8");
		if(regist){
			//证明用户名可用
			response.getWriter().write("<font style='color:green'>用户名可用！</font>");
		}else{
			//证明用户名已存在
			response.getWriter().write("用户名已存在！");
		}
	}
	//用户注销的方法
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取Session对象
		HttpSession session = request.getSession();
		//让Session对象失效
		session.invalidate();
		//重定向到首页
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	// 用户注册的方法
	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取用户输入的验证码
		String reqCode = request.getParameter("reqCode");
		//获取session域中保存的验证码
		HttpSession session = request.getSession();
		String sessCode = (String) session.getAttribute("code");
		if(reqCode.equals(sessCode)){
			//证明输入的验证码正确，正常处理请求
			//并且将session域中的验证码移除
			session.removeAttribute("code");
			// 获取用户输入的用户名、密码、邮箱
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			// 封装User对象
			User user = new User(null, userName, password, email);
			// 调用userService的方法验证用户名是否已存在
			boolean flag = userService.regist(user);
			if (flag) {
				// 用户名可用，将用户保存到数据库中
				userService.saveUser(user);
				// 重定向到注册成功页面
				response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
			} else {
				// 设置一个错误消息并放到request域中
				request.setAttribute("msg", "用户名已存在！");
				// 用户名已存在，转发到注册页面
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			}
		}else{
			//证明输入的验证码不正确，设置一个错误消息
			request.setAttribute("msg", "验证码不正确！");
			//转发到注册页面
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}

	// 用户登录的方法
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户名和密码
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		// 封装User对象
		User user = new User(null, userName, password, null);
		// 调用userService的方法验证用户名和密码
		User login = userService.login(user);
		if (login != null) {
			//将获取User对象放到session域中
			request.getSession().setAttribute("user", login);
			// 用户名和密码正确，重定向到登录成功页面
			response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
		} else {
			// 设置一个错误消息并把它放到request域中
			request.setAttribute("msg", "用户名或密码不正确！");
			// 用户名或密码不正确，转发到登录页面
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}
	}

}
