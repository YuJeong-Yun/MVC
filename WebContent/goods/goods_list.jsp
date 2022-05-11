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
			<h1>등록 상품 목록(관리자용)</h1>
			<table id="notice">
				<tr>
					<th class="ttitle" colspan="2">
						<a href="./GoodsAdd.ag">상품<br>등록<br>하기</a>
					</th>
				</tr>
				<tr>
					<th class="tno" colspan="4">
					전체 &nbsp;&nbsp;&nbsp;&nbsp;
					인기상품 &nbsp;&nbsp;&nbsp;&nbsp;
					외투 &nbsp;&nbsp;&nbsp;&nbsp;
					정장 &nbsp;&nbsp;&nbsp;&nbsp;
					티셔츠 &nbsp;&nbsp;&nbsp;&nbsp;
					와이셔츠 &nbsp;&nbsp;&nbsp;&nbsp;
					바지 &nbsp;&nbsp;&nbsp;&nbsp;
					신발 &nbsp;&nbsp;&nbsp;&nbsp;
					</th>
				</tr>
				
				<!-- 등록된 상품의 수 -->
				<c:set var="size" value="${goodsList.size() }"></c:set>
				<!-- 컬럼의 수 (가로출력 수) -->
				<c:set var="col" value="4"></c:set>
				<!-- 행의 수(세로출력) -->
				<c:set var="row" value="${size/col + (size%col>0? 1:0) }"></c:set>
				
				<!-- 상품개수 체크 -->
				<c:set var="goodsCnt" value="0"></c:set>
				
				
				<!-- 1) 행 만들기 -->
				<c:forEach begin="1" end="${row }" step="1">
					<tr>
						<!-- 2) 열 만들기 -->
						<c:forEach begin="1" end="${col }" step="1">
							<td>
								<img src="./shopUpload/${goodsList[goodsCnt].image.split(',')[0] }" width="160" height="160" /><br>
								${goodsList[goodsCnt].name }<br>
								${goodsList[goodsCnt].price }원<br>
							</td>
							<c:set var="goodsCnt" value="${goodsCnt+1 }"></c:set>
						</c:forEach>
					</tr>
				</c:forEach>
				
				
<%-- 					<c:forEach var="goods" items="${requestScope.goodsList }" begin="0" end="3"> --%>
<!-- 						<td> -->
<%-- 							<img src="./shopUpload/${goods.image.split(',')[0] }" width=160 height="160" /><br>	 --%>
<%-- 							${goods.name }<br> --%>
<%-- 							${goods.price }원<br> --%>
<!-- 						</td> -->
<%-- 					</c:forEach> --%>
					
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