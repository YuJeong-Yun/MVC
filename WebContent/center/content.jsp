<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
 <script src="./script/jquery-3.6.0.min.js"></script>
 <script type="text/javascript">
 	$(function(){
		// 수정 버튼 클릭 시 수정 페이지로 이동(정보 포함)
 		$('#upBtn').click(function(){
 			// 폼태그의 action 페이지를 설정
 			$('#fr').attr('action','./BoardUpdate.bo');
 			// 글번호를 가지고 수정페이지로 이동(submit)
 			$('#fr').submit();
 		}); // 수정버튼

 		// 삭제버튼
 		$('#delBtn').click(function() {
 			$('#fr').attr('action', './BoardDelete.bo');
 			$('#fr').submit();
 		}); // 삭제버튼
 		
 		
 	}); //jQuery
 </script>
 
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
		<div id="sub_img_center"></div>
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="#">Notice</a></li>
				<li><a href="#">Public News</a></li>
				<li><a href="#">Driver Download</a></li>
				<li><a href="#">Service Policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->

		<!-- 게시판 -->
		<article>
			<h1>model2 게시판(MVC)</h1>
			
			
			<!-- jQuery를 사용하여 submit 동작 실행  -->
			<!-- form 태그 안에 submit 버튼이 있어야 동작함 -->
			<form method="get" id="fr">
				<input type="hidden" name="num" value="${dto.num }">
				<input type="hidden" name="pageNum" value="${pageNum }">
			</form>
			
			<table id="notice">
				<tr>
					<th class="ttitle" colspan="5">게시판 본문(내용)</th>
				</tr>
				<tr>
					<td>글번호</td>
					<td>${dto.num }</td>
					<td>조회수</td>
					<td>${dto.readcount }</td>
				</tr>
				<tr>
					<td>글쓴이</td>
					<td>${dto.name }</td>
					<td>작성일</td>
					<!-- 데이터 포매팅하기 (태그 라이브러리 include 해야함) -->
					<td><fmt:formatDate value="${dto.date }" pattern="yy-MM-dd"/></td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3">${dto.subject }</td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td colspan="4">${dto.file }</td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="4">${dto.content }</td>
				</tr>
			</table>
			<div id="table_search">
			<!-- 글번호(num), 페이지넘버(pageNum) -->
				<input type="submit" value="수정" class="btn" id="upBtn">
				<input type="submit" value="삭제" class="btn" id="delBtn">
				<input type="submit" value="답글" class="btn" id="reBtn" onclick="location.href='./BoardRewrite.bo?num=${dto.num}&re_ref=${dto.re_ref }&re_lev=${dto.re_lev }&re_seq=${dto.re_seq }'; ">
				<input type="button" value="목록" class="btn" onclick="location.href='./BoardList.bo?pageNum=<%=request.getParameter("pageNum") %>';">
			</div>
			<div class="clear"></div>
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