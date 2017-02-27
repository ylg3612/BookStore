package com.atguigu.bookstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一设置字符集的Filter
 */
public class EncodeFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//获取在web.xml中设置的字符集
		FilterConfig config2 = getConfig();
		//获取ServletContext对象
		ServletContext servletContext = config2.getServletContext();
		String encode = servletContext.getInitParameter("encode");
		//设置字符集
		request.setCharacterEncoding(encode);
		//放行请求
		chain.doFilter(request, response);
	}

}
