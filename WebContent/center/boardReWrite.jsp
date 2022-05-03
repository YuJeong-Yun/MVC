<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
			<!-- action="" 이동할 페이지 정보가 없을 경우
				자기자신의 페이지로 이동
			 -->
			<form action="./BoardReWriteAction.bo" method="post">
				<input type="hidden" name="num" value="${param.num }"> <input
					type="hidden" name="re_ref" value="${param.re_ref }"> <input
					type="hidden" name="re_lev" value="${param.re_lev }"> <input
					type="hidden" name="re_seq" value="${param.re_seq }">


				<table id="notice">
					<tr>
						<th class="ttitle" colspan="5">답글 쓰기</th>
					</tr>
					<tr>
						<td>글쓴이</td>
						<td><input type="text" size="25" name="name"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" size="25" name="pass"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" size="25" name="subject" value="[답글]"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea rows="15" cols="27" name="content"></textarea></td>
					</tr>
				</table>
				<div id="table_search">
					<input type="submit" value="답글쓰기" class="btn">
				</div>
			</form>
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