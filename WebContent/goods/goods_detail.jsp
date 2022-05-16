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
 <script type="text/javascript">
 function isBasket(){
	  // alert('test!!!');
	  // 데이터 유효성 체크 (구매수량,옵션)
	  
	  // 최소 수량 체크
	  if(document.gfr.amount.value < 1){
		  alert("최소 구매 수량은 1개 입니다.");
		  document.gfr.amount.focus();
		  document.gfr.amount.value = 1;
		  return;
	  }
	  
	  // 최대 수량 체크
	  if(document.gfr.amount.value > ${dto.amount}){
		  alert("최대 구매 수량은 ${dto.amount}개 입니다.");
		  document.gfr.amount.select();
		  return;
	  }
	  
	  // 사이즈
	  if(document.gfr.size.value == ""){ // 옵션선택 X
		  alert(" 사이즈를 선택하시오. ");
	      document.gfr.size.focus();
	      return;
	  }
	  
	  // 컬러
	  if(document.gfr.color.value == ""){ // 옵션선택 X
		  alert(" 컬러를 선택하시오. ");
	      document.gfr.color.focus();
	      return;
	  }
	  
	  ////////////////////////////////////////////////////
	  // 옵션 선택완료
	  var result = confirm("장바구니로 이동하겠습니까?");
	  
	  if(result){ //result == true 
		  // 장바구니 페이지 이동 (submit)
	     document.gfr.action="./BasketAdd.ba";
		 document.gfr.submit();		  
		  
	  }else{
		  //  ajax 사용해서 정보만 저장
		  return;		  
	  }
 		
 	} // isBasket
 	
 </script>
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
		
		<form action="" method="post" name="gfr">
			<input type="hidden" name="num" value="${dto.num }">	
			<table id="notice">
				<tr>
				    <td>
				    	<img src="./shopUpload/${dto.image.split(',')[0] }" 
				    	   width="300" height="300">
				    </td>
				    <td>
				        <h2>상품명 : ${dto.name } </h2>
				        <h2>가격 : ${dto.price }원</h2>
				        <h2>구매수량 : <input type="number" name="amount">개</h2>
			 			<h2>남은수량 : ${dto.amount }개</h2>
				        크기 :<%--  ${dto.size } --%>
				            <select name="size">
				               <option value=""> 크기를 선택하시오 </option>
				               <c:forTokens var="size" items="${dto.size }" delims=",">
				               		<option value="${size }">${size }</option>
				               </c:forTokens>
				            </select> <br>
				            
				        컬러 :
				            <select name="color">
				               <option value=""> 컬러를 선택하시오 </option>
				               <c:forTokens var="color" items="${dto.color }" delims=",">
				                    <option value="${color }">${color }</option>
				               </c:forTokens>               
				            </select> <br>
							
					         <a href="javascript:isBasket()">[장바구니 담기]</a><br>
					         <a href="#">[바로 구매하기]</a><br>   
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