package com.atguigu.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 专门用来被继承的Filter
 * @author HanYanBing
 *
 */
public abstract class HttpFilter implements Filter{

	private FilterConfig config;
	
	//获取cofig的方法
	public FilterConfig getConfig(){
		return config;
	}
	//写一个专门用来被重新的初始化的方法
	public void init(){
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		this.init();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req;
		HttpServletResponse res;
		req = (HttpServletRequest)request;
		res = (HttpServletResponse)response;
		//调用重载的doFilter方法
		doFilter(req, res, chain);
	}
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException;

	@Override
	public void destroy() {}

}
