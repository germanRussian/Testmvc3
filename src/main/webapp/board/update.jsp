<%@page import="domain.BoardVO"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
BoardVO vo = (BoardVO) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정 화면</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	홈화면 > 게시판 수정하기
	<hr>

	<!-- enctype="multipart/form-data" 작성시 request 타입으로 못받는다 -->
	<form action="UpdateCon" method="post" enctype="multipart/form-data">
		<div>
			<!-- <span> 게시글 번호</span> -->
			<input type="hidden" name="num" id="num" value="<%=vo.getNum()%>"
				style="border: 0 solid black" readonly="readonly">
			<input type="hidden" name="rfn" id="rfn" value="<%=vo.getRealFileName()%>" 
			 	style="border: 0 solid black" readonly="readonly">
			<input type="hidden" name="rsfn" id="rsfn" value="<%=vo.getRealSaveFileName() %>" 
			 	style="border: 0 solid black" readonly="readonly">
		</div>
		<div>
			<span>제목 : </span> <input style="width: 450px; border: 0 solid black"
				type="text" name="title" value="<%=vo.getTitle()%>">
		</div>
		<hr>
		<div>
			<span>내용</span><br>
			<textarea rows="10" cols="100" name="content"><%=vo.getContent()%></textarea>
		</div>
		<hr>
		<div>
			<span>작성자</span> <input style="width: 450px; border: 0 solid black"
				type="text" name="writer" value="<%=vo.getWriter()%>"
				readonly="readonly">
		</div>
		<hr>
		<div>
			
			기존 파일 : <a href="download.jsp?rsfn=<%=vo.getRealSaveFileName()%>&rfn=<%=vo.getRealFileName()%>"><%=vo.getRealFileName()%></a>
			<br>
			수정 파일 : <input type="file" name="updatefile" value="<%=vo.getRealFileName()%>"> 
		</div>
		</div>
		<hr>
		<br>
		<button class="btn btn-default" onclick="location.href='ListCon'" type="button">목록</button>
		<button class="btn btn-default">수정</button>


	</form>


</body>
</html>