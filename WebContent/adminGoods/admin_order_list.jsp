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
		
<%
	// request 영역에 글정보(list) 저장
	// request.setAttribute("boardList", boardList);
// 	List boardList = (List)request.getAttribute("boardList");
// 	System.out.println(" V : " + boardList);
// 	String pageNum = (String)request.getAttribute("pageNum");
// 	System.out.println("pageNum "+pageNum);
// 	int result = (int)request.getAttribute("result"); // 글 개수
// 	System.out.println("result : "+result);
// 	int pageCount = (int)request.getAttribute("pageCount");
// 	System.out.println("pageCount "+pageCount);
// 	int pageBlock = (int)request.getAttribute("pageBlock");
// 	System.out.println("pageBlock"+pageBlock);
// 	int startPage = (int)request.getAttribute("startPage");
// 	System.out.println("startPage"+startPage);
// 	int endPage = (int) request.getAttribute("endPage");
// 	System.out.println("endPage"+endPage);

%>

		<!-- 게시판 -->
		<article>
			<h1>주문 목록</h1>
			<table id="notice">
				<tr>
					<th class="tno">주문번호</th>
					<th class="tno">사용자명</th>
					<th class="ttitle">상품명</th>
					<th class="ttitle">결제방법</th>
					<th class="ttitle">주문금액</th>
					<th class="ttitle">주문상태</th>
					<th class="ttitle">주문일시</th>
					<th class="ttitle">운송장번호</th>
				</tr>
				
				<c:forEach var="ordto" items="${adminOrderList  }">
					<tr>
						<td>
							<a href="./OrderDetail.or?trade_num=${ordto.o_trade_num }">${ordto.o_trade_num }</a>
						</td>
						<td>${ordto.o_m_id }</td>
						<td>${ordto.o_g_name }</td>
						<td>${ordto.o_trade_type }</td>
						<td>${ordto.o_sum_money }원</td>
						<!-- 0-대기중, 1-발송준비, 2-발송완료, 3-배송중, 4-배송완료, 5-주문취소 -->
						<c:if test="${ordto.o_status == 0 }"> <td>대기중</td> </c:if>
					    <c:if test="${ordto.o_status == 1 }"> <td>발송준비</td> </c:if>
					    <c:if test="${ordto.o_status == 2 }"> <td>발송완료</td> </c:if>
					    <c:if test="${ordto.o_status == 3 }"> <td>배송중</td> </c:if>
					    <c:if test="${ordto.o_status == 4 }"> <td>배송완료</td> </c:if>
					    <c:if test="${ordto.o_status == 5 }"> <td>주문취소</td> </c:if>
					    
						<td>${ordto.o_date }</td>
						<td>${ordto.o_trans_num }</td>
						
						<td>
							<a href="./AdminOrderModify.ag?trade_num=${ordto.o_trade_num }">수정</a>
						</td>
					</tr>
				</c:forEach>
				
				
				
			</table>
			
			
			<div id="table_search">
				<hr>
				<input type="button" value="글쓰기" id="btn" onclick="location.href='./BoardWrite.bo';">
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