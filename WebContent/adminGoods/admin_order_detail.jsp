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
			<h1>주문 상세내역 (${orderDetailList[0].o_trade_num })</h1>
			<form action="./AdminOrderUpdate.ag" method="post">
				<input type="hidden" name="trade_num" value="${orderDetailList[0].o_trade_num }">
			
				<table id="notice">
					<tr>
						<td>상품명</td>
						<td>구매 수량</td>
						<td>컬러</td>
						<td>사이즈</td>
					</tr>

					<c:forEach var="dto" items="${orderDetailList }">
						<tr>
							<td>${dto.o_g_name }</td>
							<td>${dto.o_g_amount }</td>
							<td>${dto.o_g_color }</td>
							<td>${dto.o_g_size }</td>
						</tr>
					</c:forEach>

				<tr>
					<td colspan="6">
					  <h1>결제 정보</h1>
					</td>
				</tr>
			    <tr>
			       <td colspan="3"> 주문상태 :</td> 
			       <td>
			          <select name="status">
			            <option value="0"  
			             <c:if test="${orderDetailList[0].o_status == 0 }">
			            	selected             
			             </c:if>            
			            >대기중</option>
			            <option value="1" 
			              <c:if test="${orderDetailList[0].o_status == 1 }">
			            	selected             
			             </c:if>  
			            >발송준비</option>
			            <option value="2" 
			               <c:if test="${orderDetailList[0].o_status == 2 }">
			            	selected             
			             </c:if>  
			            >발송완료</option>
			            <option value="3"
			                <c:if test="${orderDetailList[0].o_status == 3 }">
			            	selected             
			             </c:if>  
			            >배송중</option>
			            <option value="4" 
			               <c:if test="${orderDetailList[0].o_status == 4 }">
			            	selected             
			             </c:if>  
			             >배송완료</option>
			            <option value="5" 
			               <c:if test="${orderDetailList[0].o_status == 5 }">
			            	selected             
			             </c:if>  
			            >주문취소</option>
			          </select>
			       </td> 
			    </tr>
				<tr>
					<td colspan="2">운송장 번호</td>
					<td colspan="2"><input type="text" name="trans_num" value="${orderDetailList[0].o_trans_num }"></td>
				</tr>
				
			</table>
				
			<div id="table_search">
				<input type="submit" value="수정하기" class="btn">
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



