<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
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
                <li><a href="qanda_view.do">Q&#38;A</a></li>
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
        <div class="row">
                <table class="table table-striped"
                    style="text-align: center; border: 1px solid #dddddd">
                   <thead>
                        <tr>
                            <th colspan="3"
                                style="background-color: #eeeeee; text-align: center;">Q&#38;A</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="width: 20%;">제 목 </td>
                            <td colspan="2">${content_view.qTitle}</td>
                        </tr>
                        <tr>
                            <td>작 성 자</td>    
                            <td colspan="2">${content_view.qUserId}</td>
                        </tr>
                        <tr>
                            <td>작 성 일</td>    
                            <td colspan="2">${content_view.qDate}</td>
                        </tr>
                        <tr>
                            <td>내 용</td> 
                        </tr>
                        <tr >
                            <td colspan="3" style="min-height: 200px; text-align: left;">
                            <textarea class="form-control" style = "width : 1200px;height : 300px;padding-top: 10px;" disabled>
                                ${content_view.qContent}
                            </textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>    
                <a href = "qanda_view.qdo" class="btn btn-primary">목록</a>          
                    <%
                //글작성자 본인일시 수정 삭제 가능
                    if(userId.equals(session.getAttribute("contentId")) || userId.equals("admin")){
                    %>
                        <a href="qanda_modify_view.qdo?qId=${content_view.qId}" class="btn btn-primary">수정</a>
                        <a onclick="return confirm('정말로 삭제하시겠습니까?')" href="qanda_delete.qdo?qId=${content_view.qId}" class="btn btn-primary">삭제</a>
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