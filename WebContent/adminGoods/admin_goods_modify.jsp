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
		<!-- 헤더가 들어가는 곳 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<!-- 헤더가 들어가는 곳 -->

		<!-- 본문 들어가는 곳 -->
		<!-- 서브페이지 메인이미지 -->
		<div id="sub_img"></div>
		<!-- 서브페이지 메인이미지 -->
		<!-- 왼쪽메뉴 -->
		<jsp:include page="../inc/left.jsp"></jsp:include>
		<!-- 왼쪽메뉴 -->
		<!-- 내용 -->
		<!-- 게시판 -->
		<article>
			<h1>관리자 상품수정(WILLSHOP)</h1>
			<!-- GoodsModifyAction으로 하면 컨트롤러에서 주소가 GoodsModify 일때
			GoodsModifyAction 클래스와 이름이 같으므로 헷갈리지 않게 Pro 붙여줌-->
			<form action="./GoodsModifyProAction.ag" method="post">
				<input type="hidden" name="num" value="${dto.num }">
				<table id="notice">
					<tr>
						<th class="ttitle" colspan="5">관리자 상품수정</th>
					</tr>
					<tr>
						<td>상품카테고리</td>
						<td>
							<select name="category">
								<option value="" >상품 카테고리를 선택하세요.</option>
								<option value="outwear"
									<c:if test="${dto.category == 'outwear' }">
										selected="selected"
									</c:if>
								>외투</option>								
								<option value="fulldress"
									<c:if test="${dto.category == 'fulldress' }">
										selected="selected"
									</c:if>
								>정장</option>								
								<option value="Tshirts"
									<c:if test="${dto.category == 'Tshirts' }">
										selected="selected"
									</c:if>
								>티셔츠</option>								
								<option value="shirts"
									<c:if test="${dto.category == 'shirts' }">
										selected="selected"
									</c:if>
								>와이셔츠</option>								
								<option value="pants"
									<c:if test="${dto.category == 'pants' }">
										selected="selected"
									</c:if>
								>바지</option>								
								<option value="shoes"
									<c:if test="${dto.category == 'shoes' }">
										selected="selected"
									</c:if>
								>신발</option>								
							</select>
						</td>
					</tr>
					<tr>
						<td>상품이름</td>
						<td><input type="text" name="name" value="${dto.name }"></td>
					<tr>
						<td>판매가</td>
						<td><input type="number" name="price" value="${dto.price }"></td>
					<tr>
						<td>컬러</td>
						<td><input type="text" name="color" placeholder="ex) red, blue, green, ..." value="${dto.color }"></td>
					<tr>
						<td>수량</td>
						<td><input type="number" name="amount" value="${dto.amount }"></td>
					</tr>
					<tr>
						<td>사이즈</td>
						<td><input type="text" name="size" placeholder="ex) XL, L, M, S, FREE, ..." value="${dto.size }"></td>
					</tr>
					<tr>
						<td>제품정보</td>
						<td><input type="text" name="content" value="${dto.content }"></td>
					</tr>
					<tr>
						<td>인기상품</td>
						<td>
							<input type="radio" name="best" value="1"
								<c:if test="${dto.best == 1 }" >
									checked="chedcked"
								</c:if>
							> 예
							<input type="radio" name="best" value="0"
								<c:if test="${dto.best == 0 }" >
									checked="chedcked"
								</c:if>
							> 아니오
						</td>
					</tr>
				</table>
				<div id="table_search">
					<input type="submit" value="상품 수정하기" class="btn">
				</div>
			</form>
			<div class="clear"></div>
		</article>
		<!-- 게시판 -->
		<!-- 내용 -->
		<!-- 본문 들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터 들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
		<!-- 푸터 들어가는 곳 -->
	</div>
</body>
</html>



