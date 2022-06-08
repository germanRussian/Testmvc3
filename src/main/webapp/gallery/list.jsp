<%@page import="domain.BoardInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="domain.BoardVO"%>
<%@page import="java.util.Collection"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
BoardInfo boardInfo = (BoardInfo) request.getAttribute("boardInfo");
Collection<BoardVO> list = boardInfo.getList();//글 목록
int totalRow = boardInfo.getTotalRow();//글 목록 수

int totalNum = (Integer) request.getAttribute("totalNum");
int pageNum = (Integer) request.getAttribute("pageNum");
int pageRow = (Integer) request.getAttribute("pageRow");

int pagingNum = (Integer) request.getAttribute("pagingNum");
int startNum = (Integer) request.getAttribute("startNum");

String field = (String) request.getAttribute("field");
String keyWord = (String) request.getAttribute("keyWord");

String field1 = (String) request.getAttribute("field1");
String keyWord1 = (String) request.getAttribute("keyWord1");
String keyWord2 = (String) request.getAttribute("keyWord2");
String keyWord3 = (String) request.getAttribute("keyWord3");

int lastPage = totalRow / pageRow + ((totalRow % pageRow == 0) ? 0 : +1);

//주소창에서 임의 변경 불가하게 만듦
//if (pageNum > lastPage || pageNum < 1) {
//	response.sendRedirect("http://localhost/Testmvc2/ListCon?pageNum=1");
//}

//글 순번 음수 방지
/* if(totalNum<1){
	totalNum = null;
} */

//Collection<BoardVO> list = (Collection)request.getAttribute("list");
//int totalRow = (Integer)request.getAttribute("totalRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
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

	<div class="container">
		<h5>홈 >> 게시판 목록</h5>
		<hr>

		<!--  -->
		<div>
			<div class="pull-left">
				전체글수 :
				<%=totalRow%>
			</div>
			<%-- <div class="pull-right" style="width: 350px;">
				<form action="">
					<select name="field" class="form-control"
						style="display: inline-block; width: 50%">

						<option value="">선택</option>
						<option value="title"
							<%="title".equals(field) ? "selected='selected'" : ""%>>제목</option>

						<option value="writer"
							<%="writer".equals(field) ? "selected='selected'" : ""%>>작성자</option>

						<option value="content"
							<%="content".equals(field) ? "selected='selected'" : ""%>>내용</option>

						<option value="titleContent"
							<%="titleContent".equals(field) ? "selected='selected'" : ""%>>제목
							or 내용</option>
						<option value="titleContentAnd"
							<%="titleContentAnd".equals(field) ? "selected='selected'" : ""%>>제목
							and 내용</option>

					</select> <input type="text" name="keyWord" class="form-control"
						style="display: inline-block; width: 50%" value="<%=keyWord%>">
					<button class="btn btn-default">검색</button>
				</form>
			</div> --%>
		</div>
		<br>
		<div>
			<form action="" name="field1">
				<select name="field1" class="form-control"
					style="display: inline-block; width: 50%">

					<option value="">선택</option>

					<option value="title"
						<%="title".equals(field1) ? "selected='selected'" : ""%>>제목</option>

					<option value="titleWriter"
						<%="titleWriter".equals(field1) ? "selected='selected'" : ""%>>제목
						+ 작성자</option>

					<option value="titleWriterContent"
						<%="content".equals(field1) ? "selected='selected'" : ""%>>제목
						+ 작성자 +내용</option>


				</select> <input type="text" name="keyWord1" class="form-control"
					style="display: inline-block; width: 50%" value="<%=keyWord1%>"
					placeholder="제목"> <input type="text" name="keyWord2"
					class="form-control" style="display: inline-block; width: 50%"
					value="<%=keyWord2%>" placeholder="작성자"> <input type="text"
					name="keyWord3" class="form-control"
					style="display: inline-block; width: 50%" value="<%=keyWord3%>"
					placeholder="내용">
				<button class="btn btn-default">종합 검색</button>
			</form>

		</div>

		<!--  -->
		<div class="row" style="clear: both; padding: 20px 0px">

			<%
			Iterator<BoardVO> it = list.iterator();
			while (it.hasNext()) {
				BoardVO vo = it.next();
			%>
			<div style="float: left; padding: 5px;">
				<div style="height: 170px; overflow: hidden;">
					<a href="ViewCon?num=<%=vo.getNum()%>"><%=(vo.getRealSaveFileName() != null && !"".equals(vo.getRealSaveFileName())) ? "<img src='upload/sm_" + vo.getRealSaveFileName() + "' style='width:255.99px;  '/>" : "<img src='img/no_img.png' style='width:255.99px; ' />"%></a>
				</div>
				<div><a href="ViewCon?num=<%=vo.getNum()%>"><%=vo.getTitle()%></a></div>

			</div>
			<%
			}
			%>

		</div>






		<!-- Paging -->
		<nav style="text-align: center;">

			<ul class="pagination">
				<%
				if (pageNum == 1) {
				%>
				<li class="disabled"><span aria-hidden="true">&laquo;</span></li>
				<%
				} else {
				%>
				<li><a href="?pageNum=<%=pageNum - 1%>&keyWord=<%=keyWord%>"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<%
				}
				%>

				<%
				for (int i = startNum; i <= (startNum + pagingNum) - 1; i++) {
					if (i > lastPage)
						break;
					if (pageNum == i) {
				%>
				<li class="active"><a><%=i%></a></li>
				<%
				} else {
				%>
				<li><a
					href="?pageNum=<%=i%>&field=<%=field%>&keyWord=<%=keyWord%>"><%=i%></a></li>
				<%
				}
				}
				%>

				<%
				if (lastPage == pageNum) {
				%>
				<li class="disabled"><span aria-hidden="true">&raquo;</span></li>
				<%
				} else {
				%>
				<li><a href="?pageNum=<%=pageNum + 1%>&keyWord=<%=keyWord%>"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
				<%
				}
				%>

			</ul>
		</nav>
		<div class="pull-right">
			<a href="WriterCon" class="btn btn-default">글쓰기</a>
		</div>
	</div>
</body>
</html>
