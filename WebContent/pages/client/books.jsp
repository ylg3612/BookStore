<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		//给加入购物车按钮绑定单击事件
		$(".addBook").click(function(){
			//获取图书的id
			var bookId = this.id;
			//向Servlet发请求将图书添加到购物车
			window.location.href = "CartServlet?method=addBook2Cart&bookId="+bookId;
		});
	});
</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/welcome.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
			<form action="BookClientServlet" method="get">
					<input type="hidden" name="method" value="getPageBooksByPrice">
				价格：<input type="text" name="min"> 元 - 
					<input type="text" name="max"> 元 
					<input type="submit" value="查询">
			</form>		
			</div>
			<div style="text-align: center">
				<c:if test="${empty cart.cartItems }">
					<span>您的购物车空空如也</span>
				</c:if>
				<c:if test="${not empty cart.cartItems }">
					<span>您的购物车中有${cart.totalCount }件商品</span>
				</c:if>
				<div>
				 <c:if test="${empty msg }">
					<c:if test="${!empty bookTitle }">
						您刚刚将<span style="color: red">${bookTitle }</span>加入到了购物车中
						<c:remove var="bookTitle"/>
					</c:if>
				</c:if>
				<c:if test="${not empty msg }">
					<span style="color: red">${msg }</span>
					<c:remove var="msg"/>
					<c:remove var="bookTitle"/>
				</c:if>	
				</div>
			</div>
		<c:forEach items="${page.list }" var="book">	
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath }" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.title }</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author }</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${book.price }</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales }</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock }</span>
					</div>
					<div class="book_add">
						<c:if test="${book.stock > 0 }">
							<button class="addBook" id="${book.id }">加入购物车</button>
						</c:if>	
						<c:if test="${book.stock < 1 }">
							缺货
						</c:if>	
					</div>
				</div>
			</div>
		</c:forEach>	
			
		</div>
		<!-- 使用静态包含将页码包含进来 -->
		<%@ include file="/WEB-INF/include/page.jsp" %>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>