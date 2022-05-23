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
			<h1>주문 상세내역</h1>
			<form action="./OrderAdd.or" method="post">
				<table id="notice">
					<tr>
						<td>이미지</td>
						<td>상품명</td>
						<td>구매 수량</td>
						<td>컬러</td>
						<td>사이즈</td>
						<td>가격</td>
					</tr>
					
					<!-- 주문 총금액 -->
					<c:set var="totalPrice" value="0"/>
					
					<c:forEach var="i" begin="0" end="${basketList.size()-1 }" step="1">
						<c:set var="basket" value="${basketList[i] }" />
						<c:set var="goods" value="${goodsList[i] }" />
							<tr>
								<td>
									<img src="./shopUpload/${goods.image.split(',')[0] }" width="50" />
								</td>
								<td>${goods.name }</td>
								<td>${basket.b_g_amount }</td>
								<td>${basket.b_g_color }</td>
								<td>{basket.b_g_size</td>
								<td>${goods.price }</td>
							</tr>
							
							<!-- 누적합 계산(금액) -->
							<c:set var="totalPrice" value="${totalPrice + (basket.b_g_amount*goods.price)}"></c:set>
					</c:forEach>
				
				<tr>
					<td colspan="6">총 : <f:formatNumber value="${totalPrice }"  />원 </td>
				</tr>
				
				<tr>
					<td colspan="6">
					  <h1>주문자 정보</h1>
					</td>
				</tr>
				
				<tr>
				  <td colspan="2">이름 : ${sessionScope.id }</td>
				  <td colspan="2">휴대폰 : ${memberDTO }</td>
				  <td colspan="2">이메일주소 : ${memberDTO }</td>
				</tr>
				
				<tr>
					<td colspan="6">
					  <h1>배송지 정보</h1>
					</td>
				</tr>
				<tr>
				  <td>받는사람 : </td>
				  <td colspan="2"><input type="text" name="o_r_name"> </td>
				  <td>연락처 : </td>
				  <td colspan="2"><input type="text" name="o_r_phone"> </td>
				</tr>

				<tr>
				  <td>배송지 주소 : </td>
				  <td colspan="2"><input type="text", name="o_r_addr1"> </td>
				  <td>배송지 나머지주소 : </td>
				  <td colspan="2"><input type="text" name="o_r_addr2"> </td>
				</tr>
				<tr>
				  <td>메모 : </td>
				  <td colspan="5"><textarea rows="1" cols="75" name="o_r_msg"></textarea> </td>
				</tr>
				
								
				<tr>
					<td colspan="6">
					  <h1>결제 정보</h1>
					</td>
				</tr>
				<tr>
					<td>
						<input type="radio" name="o_trade_type" value="온라인입금">온라인 입금
					</td>
					<td colspan="2">
						<input type="text" name="o_trade_payer" placeholder="온라인 입금자명">
					</td>
					<td>
						<input type="radio" name="o_trade_type" value="신용카드">신용카드
					</td>
					<td>
						<input type="radio" name="o_trade_type" value="네이버페이">네이버페이
					</td>
					<td>
						<input type="radio" name="o_trade_type" value="카카오페이">카카오페이
					</td>
				</tr>
				
				
			</table>
				
			<div id="table_search">
				<input type="submit" value="주문하기" class="btn">
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



