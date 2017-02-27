package com.atguigu.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取连接和释放连接的工具类
 * @author HanYanBing
 *
 */
public class JDBCUtils {

	//获取数据源
	public static DataSource dataSource = new ComboPooledDataSource();
	
	//创建ThreadLocal对象
	public static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	/**
	 * 获取连接
	 * @return
	 */
//	public static Connection getConnection(){
//		Connection connection = null;
//		try {
//			connection = dataSource.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return connection;
//	}
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection(){
		//从当前线程中获取连接
		Connection connection = threadLocal.get();
		if(connection == null){
			try {
				//从连接池中获取一个连接
				connection = dataSource.getConnection();
				//将连接设置到当前线程中
				threadLocal.set(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	/**
	 * 释放连接
	 * @param connection
	 */
//	public static void releaseConnection(Connection connection){
//		if(connection != null){
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	public static void releaseConnection(){
		//获取当前线程中的连接
		Connection connection = threadLocal.get();
		if(connection != null){
			try {
				//将连接释放
				connection.close();
				//将当前线程中关闭的连接移除
				threadLocal.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
