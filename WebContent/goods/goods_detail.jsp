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
			<h1>등록 상품 상세페이지(사용자용)</h1>
			<form>
			<table id="notice">
				<tr>
					<td><img src="./shopUpload/${dto.image.split(',')[0] }" width="300" height="300"></td>
					<td>
						<h2>상품명 : ${dto.name }<br></h2>
						<h2>가격 : ${dto.price }<br></h2>
						<h2>구매수량 : <input type="number" name="amount">개</h2>
						<h2>남은수량 : ${dto.amount }<br></h2>
						크기 : 
							<select name="size">
								<option> 크기를 선택하세요 </option>
								<c:forEach var="size" items="${dto.size }">
									<option>${size }</option>
								</c:forEach>
							</select name="color"> <br>
						컬러 : 
							<select>
								<option> 컬러를 선택하세요 </option>
								<c:forEach var="color" items="${dto.color }">
									<option>${color }</option>
								</c:forEach>
							</select> <br>
						
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<img src="./shopUpload/${dto.image.split(',')[1] }" width="600" height="300">
						<img src="./shopUpload/${dto.image.split(',')[2] }" width="600" height="300">
						<img src="./shopUpload/${dto.image.split(',')[3] }" width="600" height="300">
					</td>
				</tr>
				
				<tr>
					<td coslpan="2">
						<h1>QnA / 질문게시판 / 리뷰</h1>
					</td>
				</tr>
			</table>
			</form>
			
			
			<div id="table_search">
				<hr>
				<input type="button" value="글쓰기" id="btn" onclick="location.href='./BoardWrite.bo';">
			</div>
			<div class="clear"></div>
			
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