<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		//给删除购物项的超链接绑定单击事件
		$(".deleteA").click(function(){
			//获取图书的名字
			var bookTitle = this.id;
			var flag = confirm("亲！您真的要删除【"+bookTitle+"】这本图书吗？三思啊！！！");
			return flag;
		});
		//给清空购物车的超链接绑定单击事件
		$("#clearCart").click(function(){
			var flag = confirm("您确定要清空购物车吗？");
			return flag;
		});
		//给图书数量输入框绑定change事件
		$(".inputV").change(function(){
			//获取购物项中图书的默认数量
			var defValue = this.defaultValue;
			//获取图书的id
			var bookId = this.id;
			//获取用户输入的图书的数量
			var bookCount = this.value;
			//对用户输入的值进行合法性的验证
			//声明一个验证用户输入的值是否是 一个正整数的正则表达式
			var reg = /^\+?[1-9][0-9]*$/;
			if(!reg.test(bookCount)){
				//将文本框中的值恢复默认值
				this.value = defValue;
				alert("请输入正整数！")
				return false;
			}
			//获取图书的库存
			var stock = this.name;
// 			stock = parseInt(stock);
			stock = new Number(stock);
			if(bookCount > stock){
				//将文本框中的值恢复默认值
				this.value = defValue;
				alert("该图书的库存只有"+stock+"本！");
				return false;
			}else{
				//将这个不大于库存且合法的值设置给默认值
				this.defaultValue = this.value;
			}
			//向CartServlet发请求更新购物项中图书的数量
// 			location = "CartServlet?method=updateCartItem&bookId="+bookId+"&bookCount="+bookCount;
			//获取购物项中显示金额小计的td
			var $tdEle = $(this).parent().next().next();
			//设置请求地址
			var url = "CartServlet?method=updateCartItem";
			//设置请求参数
			var params = {"bookId":bookId,"bookCount":bookCount};
			//发送Ajax请求
			$.post(url,params,function(data){
				//获取购物车中的总数量
				var totalCount = data.totalCount;
				//获取购物车中的总金额
				var totalAmount = data.totalAmount;
				//获取购物项中的金额小计
				var amount = data.amount;
				//更新购物车中图书的总数量
				$("#b_count").text(totalCount);
				//更新购物车中图书的总金额
				$("#b_price").text(totalAmount);
				//更新购物项中的金额小计
				$tdEle.text(amount);
			},"json");
		});
		//获取焦点事件
// 		$(".inputV").focus(function(){
// 			alert("看我72变……")
// 		});
		//失去焦点的事件
// 		$(".inputV").blur(function(){
// 			alert("看我72变……")
// 		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/WEB-INF/include/welcome.jsp" %>
	</div>
	
	<div id="main">
		<c:if test="${empty cart.cartItems }">
			<br><br><br><br><br><br><br><br><br>
			<h1 align="center">购物车中没有任何图书，快去<a href="index.jsp" style="color: red">购物</a>吧</h1>
		</c:if>
		<c:if test="${not empty cart.cartItems }">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
		<c:forEach items="${cart.cartItems }" var="cartItem">			
			<tr>
				<td>${cartItem.book.title }</td>
				<td>
					<input name="${cartItem.book.stock }" id="${cartItem.book.id }" class="inputV" type="text" value="${cartItem.count }" style="width: 30px;text-align: center">
				</td>
				<td class="">${cartItem.book.price }</td>
				<td>${cartItem.amount }</td>
				<td><a id="${cartItem.book.title }" class="deleteA" href="CartServlet?method=deleteCartItem&bookId=${cartItem.book.id }">删除</a></td>
			</tr>		
		</c:forEach>	
		</table>
		
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count" id="b_count">${cart.totalCount }</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price" id="b_price">${cart.totalAmount }</span>元</span>
			<span class="cart_span"><a href="index.jsp">继续购物</a></span>
			<span class="cart_span"><a href="CartServlet?method=clearCart" id="clearCart">清空购物车</a></span>
			<span class="cart_span"><a href="OrderClientServlet?method=checkout">去结账</a></span>
		</div>
		</c:if>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>