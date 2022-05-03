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
		<div id="sub_img_center"></div>
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
			<jsp:include page="../inc/left.jsp"></jsp:include>
		<!-- 왼쪽메뉴 -->
		
<%
	// request 영역에 글정보(list) 저장
	// request.setAttribute("boardList", boardList);
// 	List boardList = (List)request.getAttribute("boardList");
// 	System.out.println(" V : " + boardList);
	String pageNum = (String)request.getAttribute("pageNum");
	int result = (int)request.getAttribute("result");
	int pageCount = (int)request.getAttribute("pageCount");
	int pageBlock = (int)request.getAttribute("pageBlock");
	int startPage = (int)request.getAttribute("startPage");
	int endPage = (int) request.getAttribute("endPage");

%>

		<!-- 게시판 -->
		<article>
			<h1>model2 (MVC)게시판</h1>
			<table id="notice">
				<tr>
					<th class="tno">No.</th>
					<th class="ttitle">Title</th>
					<th class="twrite">Writer</th>
					<th class="tdate">Date</th>
					<th class="tread">Read</th>
				</tr>
				
<%-- 				<%for(int i=0;i<boardList.size();i++) {  --%>
<!--  					BoardDTO dto = (BoardDTO)boardList.get(i); -->
<%-- 				%> --%>
<!-- 				<tr> -->
<%-- 					<td><%= dto.getNum() %></td> --%>
<%-- 					<td class="left"><%=dto.getSubject() %></td> --%>
<%-- 					<td><%=dto.getName() %></td> --%>
<%-- 					<td><%=dto.getDate() %></td> --%>
<%-- 					<td><%=dto.getReadcount() %></td> --%>
<!-- 				</tr> -->
<%-- 				<%} %> --%>

				<c:forEach var="dto" items="${boardList }">
					<tr>
						<td>${dto.num }</td>
						<td class="left">
							<c:if test="${dto.re_lev > 0 }">
								<img src="./images/center/level.gif" width="${dto.re_lev * 10 }">
								<img src="./images/center/re.gif">
							</c:if>
							<a href="./BoardContent.bo?num=${dto.num }&pageNum=<%=pageNum %>">${dto.subject }</a>
						</td>
						<td>${dto.name }</td>
						<td>${dto.date }</td>
						<td>${dto.readcount }</td>
					</tr>
				</c:forEach>
				
			</table>
			
			
			<div id="table_search">
				<input type="button" value="글쓰기" class="btn" onclick="location.href='./BoardWrite.bo';">
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
		   	<%
		   	if(result != 0){
		   		
			// 이전
		  		if(startPage > pageBlock){
		  		%>
		  		   <a href="./BoardList.bo?pageNum=<%=startPage-pageBlock%>">[이전]</a>
		 		<%
		   	}
		   	
		  		//  1 2 3 4 ... 10   11 12 13.... 20 
		   	for(int i=startPage;i<=endPage;i++){
		   		   %>
		   		       <a href="./BoardList.bo?pageNum=<%=i%>">[<%=i %>]</a>      		   
		   		   <% 		
		   	}
		  		
		   	// 다음
		   	if(endPage < pageCount){
		   		%>
		   		    <a href="./BoardList.bo?pageNum=<%=startPage+pageBlock%>">[다음]</a>
		   		<%
		   	}
		   	
		   	}
		  		
		   	%>
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