<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import = "com.page.Dao.BDao" %>
<%@ page import = "com.page.Dto.BDto" %>
<%@ page import = "java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- 뷰포트 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 스타일시트 참조  -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>자유게시판</title>
</head>
<body>
<%
        //로긴한사람이라면  userID라는 변수에 해당 아이디가 담기고 그렇지 않으면 null값
        String userId = null;
        if (session.getAttribute("userId") != null) {
            userId = (String) session.getAttribute("userId");
        }
       %>

<!-- 네비게이션  -->
       <nav class="navbar navbar-default">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                aria-expaned="false">
                <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main.pdo">평화로운 중고나라</a>
        </div>
        <div class="collapse navbar-collapse"
            id="#bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="main.pdo">메인</a></li>
                <li><a href="freeboard_view.do">자유 게시판</a></li>
                <li><a href="qanda_view.qdo">Q&#38;A</a></li>
            </ul>
            <%
                //라긴안된경우
                if (session.getAttribute("logon") == null){
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                    data-toggle="dropdown" role="button" aria-haspopup="true"
                    aria-expanded="false">접속하기<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="active"><a href="Login_view.jsp">로그인</a></li>
                        <li><a href="signup_view.jsp">회원가입</a></li>
                    </ul>
                </li>
            </ul>
            <%
                }else{
            %>
                 <ul class="nav navbar-nav navbar-right">
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                    data-toggle="dropdown" role="button" aria-haspopup="true"
                    aria-expanded="false"><%=userId %><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                     <%
                    if(userId.equals("admin")){
                    %>
                        <li><a href="userAdmin.jsp">유저 정보 관리</a></li>
                    <% }%>
                        <li><a href="logoutAction.jsp">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
            <%
                }
            %>
        </div>
    </nav>
	<!-- 게시판 -->
	<div class="container">
		<div class = "row">
			<table class="table table-striped" style="text-align:center; border:1px solid #dddddd"> 
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center; width:15px">번호</th>
						<th style="background-color: #eeeeee; text-align: center; width:100px">제목</th>
						<th style="background-color: #eeeeee; text-align: center; width:30px">작성자</th>
						<th style="background-color: #eeeeee; text-align: center; width:50px">작성일</th>
					</tr>
				</thead>
				<tbody>
			    <c:forEach items = "${list}" var = "dto">
                <tr>
                <td>${dto.bId}</td>
                <td><a href = "content_view.do?bId=${dto.bId}">${dto.bTitle}</a></td>
                <td>${dto.bUserId}</td>
                <td>${dto.bDate }</td>
                <tr>
                </c:forEach>
				</tbody>
			</table>	
			<!-- 페이지 넘기기 -->
			 <div class = "container" style = "text-align: center;">
                 <ul class = "pagination">  
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">이전</a></li>
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">1</a></li>
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">2</a></li>
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">3</a></li>
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">4</a></li>
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">5</a></li>
                    <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">다음</a></li>
                 </ul>
            </div>
                            <!-- 회원만넘어가도록 -->
                <%
                    //if logined userID라는 변수에 해당 아이디가 담기고 if not null
                   if (session.getAttribute("logon") != null){
                %>
                    <a href = "write_view.do" class="btn btn-primary pull-right">글쓰기</a>
                <%
                    }else {
                %>
                <button class="btn btn-primary pull-right"
                    onclick="if(confirm('로그인 하세요'))location.href='Login_view.jsp';"
                    type="button">글쓰기</button>
                <%
                    }
                %>
		</div>
	</div>
<!-- 애니매이션 담당 JQUERY -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- 부트스트랩 JS  -->
	<script src="js/bootstrap.js"></script>
</body>
</html>