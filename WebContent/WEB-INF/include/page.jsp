<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="page_nav">
		<c:if test="${page.pageNo > 1 }">
			<a href="${page.path }">首页</a>
			<a href="${page.path }&pageNo=${page.prev }">上一页</a>
		</c:if>
		<!-- 1.当总页数小于5时 -->
		<!-- 2.当总页数大于5，但是当前页小于等于3时 -->
		<!-- 3.当总页数大于5，但是当前页大于3时 -->
		<c:choose>
			<%-- 1.当总页数小于5时 --%>
			<c:when test="${page.totalPageNo < 5 }">
				<c:set var="begin" value="1"></c:set>
				<c:set var="end" value="${page.totalPageNo }"></c:set>
			</c:when>
			<%-- 2.当总页数大于5，但是当前页小于等于3时 --%>
			<c:when test="${page.pageNo <= 3 }">
				<c:set var="begin" value="1"></c:set>
				<c:set var="end" value="5"></c:set>
			</c:when>
			<%-- 当当前页加2大于总页数时 --%>
			<c:when test="${page.pageNo + 2 > page.totalPageNo }">
				<c:set var="begin" value="${page.totalPageNo - 4 }"></c:set>
			    <c:set var="end" value="${page.totalPageNo }"></c:set>
			</c:when>
			<%-- 3.当总页数大于5，但是当前页大于3时 --%>
			<c:otherwise>
				<c:set var="begin" value="${page.pageNo - 2 }"></c:set>
				<c:set var="end" value="${page.pageNo + 2 }"></c:set>
<%-- 				<c:if test="${page.pageNo + 2 > page.totalPageNo }">
<%-- 					<c:set var="begin" value="${page.totalPageNo - 4 }"></c:set> --%>
<%-- 					<c:set var="end" value="${page.totalPageNo }"></c:set> --%>
<%-- 				</c:if> --%>
			</c:otherwise>
		</c:choose>
		<c:forEach begin="${begin }" end="${end }" var="index">
			<c:if test="${page.pageNo == index }">
				【<a href="${page.path }&pageNo=${index }">${index }</a>】
			</c:if>
			<c:if test="${page.pageNo != index }">
				<a href="${page.path }&pageNo=${index }">${index }</a>
			</c:if>
		</c:forEach>	
		<c:if test="${page.pageNo < page.totalPageNo }">	
			<a href="${page.path }&pageNo=${page.next }">下一页</a>
			<a href="${page.path }&pageNo=${page.totalPageNo }">末页</a>
		</c:if>	
			&nbsp;
			当前是第${page.pageNo }页&nbsp;&nbsp;共${page.totalPageNo }页&nbsp;&nbsp;共${page.totalRecord }条记录
			 到第<input value="${page.pageNo }" name="pn" id="pn_input"/>页
			<input type="button" value="确定" id="inpBut">
			<script type="text/javascript">
				//获取按钮并给它绑定单击事件
				$("#inpBut").click(function(){
					//获取输入的页码
					var pageNo = $("#pn_input").val();
					//发送请求
// 					window.location.href = "BookManagerServlet?method=getPageBooks&pageNo="+pageNo;
// 					window.location = "BookManagerServlet?method=getPageBooks&pageNo="+pageNo;
					location = "${page.path }&pageNo="+pageNo;
				});
			</script>
</div>