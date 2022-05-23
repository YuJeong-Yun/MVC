<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
		<div id="sub_img"></div>
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
		<jsp:include page="../inc/left.jsp"></jsp:include>
		<!-- 왼쪽메뉴 -->
		
		
		<!-- 게시판 -->
		<article>
			<h1>${sessionScope.id }님의 장바구니</h1>
			<table id="notice">
				<tr>
					<th class="tno">번호</th>
					<th class="ttitle">사진</th>
					<th class="ttitle">상품명</th>
					<th class="ttitle">사이즈</th>
					<th class="ttitle">칼라</th>
					<th class="ttitle">구매 수량</th>
					<th class="ttitle">가격(개당)</th>
					<th class="ttitle">취소</th>
				</tr>
				
				<c:forEach var="i" begin="0" end="${basketList.size()-1 }" step="1">
					<c:set var="basket" value="${basketList[i] }" />
					<c:set var="goods" value="${goodsList[i] }" />
					
					<tr>
						<td>${basket.b_num }</td>
						<td><img src="./shopUpload/${goods.image.split(',')[0] }" width="50" height="50"/></td>
						<td>${goods.name }</td>
						<td>${basket.b_g_size }</td>
						<td>${basket.b_g_color }</td>
						<td>${basket.b_g_amount}</td>
						<td><f:formatNumber value="${goods.price }" />원</td>
						<td><a href="./BasketDelete.ba?b_num=${basket.b_num }">취소</a></td>
					</tr>
				</c:forEach>
				
			</table>
			
			
			<div id="table_search">
				<h3><a href="./OrderStart.or">[구매하기]</a></h3>
				<h3><a href="./GoodsList.go">[계속 쇼핑하기]</a></h3>
			
			</div>
			<div class="clear"></div>
			
			<div id="page_control">
<!-- 				이전 -->
<%-- 				<c:if test="${param.startPage } > ${param.pageBlock }"> --%>
<%-- 					<a href="boardList.jsp?pageNum=${param.startPage - param.pageBlock }">Prev</a> --%>
<%-- 				</c:if> --%>
	
<!-- 				1 2 3 4 .... 10 11 12 ...... 20 -->
<%-- 				<c:forEach var="i" begin="${param.startPage }" end="${param.endPage }"> --%>
<%-- 					<a href="boardList.jsp?pageNum=${i }">[${i }]</a> --%>
<%-- 				</c:forEach> --%>
	
<!-- 				이후 -->
<%-- 				<c:if test="${param.endPage } < ${param.pageCount }"> --%>
<%-- 					<a href="boardList.jsp?pageNum=${param.startPage + param.pageBlock }">Next</a> --%>
<%-- 				</c:if> --%>

		 <div id="page_control">
		</div>
			
			
		</article>
		<!-- 게시판 -->
		<!-- 본문들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>