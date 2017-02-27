package com.atguigu.bookstore.utils;

import javax.servlet.http.HttpServletRequest;

public class WEBUtils {

	public static String getPath(HttpServletRequest request) {
		// 获取请求路径
		String requestURI = request.getRequestURI();
		// 获取查询字符串
		String queryString = request.getQueryString();
		String path = requestURI + "?" + queryString;
		if (path.contains("&pageNo")) {
			path = path.substring(0, path.indexOf("&pageNo"));
		}
		return path;
	}
}
