<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%@ include file="/WEB-INF/include/welcome.jsp" %>
	</div>
	
	<div id="main">
		
	<c:if test="${empty orders }">
		真抠！！！一个订单都没有下……
	</c:if>
	<c:if test="${!empty orders }">
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>数量</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
		<c:forEach items="${orders }" var="order">			
			<tr>
				<td>${order.id }</td>
				<td>
					<fmt:formatDate value="${order.orderTime }" type="both"/>
				</td>
				<td>${order.totalCount }</td>
				<td>${order.totalAmount }</td>
				<td>
					<c:if test="${order.state == 0 }">
						等待发货
					</c:if>	
					<c:if test="${order.state == 1 }">
						<a href="OrderClientServlet?method=getOrder&orderId=${order.id }">确认收货</a>
					</c:if>	
					<c:if test="${order.state == 2 }">
						交易完成
					</c:if>	
				</td>
				<td><a href="OrderManagerServlet?method=getOrderItems&orderId=${order.id }">查看详情</a></td>
			</tr>	
		</c:forEach>	
		</table>
	</c:if>	
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>