<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
			<h1>주문 상세 목록</h1>
			<table id="notice">
				<tr>
					<th class="tno">상품명</th>
					<th class="ttitle">옵션-사이즈</th>
					<th class="ttitle">옵션-컬러</th>
					<th class="ttitle">주문수량</th>
					<th class="ttitle">주문가격</th>
				</tr>
				
				<c:set var="total" value="0" />
				<c:forEach var="dto" items="${detailList  }">
					<c:set var="total" value="${total+dto.o_sum_money }"></c:set>
					<tr>
						<td>${dto.o_g_name }</td>
						<td>${dto.o_g_size }</td>
						<td>${dto.o_g_color }</td>
						<td>${dto.o_g_amount }</td>
						<td>${dto.o_sum_money }</td>
					</tr>
				</c:forEach>
				
			</table>
			
			
			<div id="table_search">
				<h2>총 주문금액 : ${total }원</h2>
				<h2><a href="./OrderList.or">주문목록으로 돌아가기</a></h2>
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