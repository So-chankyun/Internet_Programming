<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import = "com.page.Dao.userInfoDao" %>
<%@ page import = "com.page.Dto.userInfoDto" %>
<!DOCTYPE html>
<html>
<head>
<!-- 뷰포트 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 스타일시트 참조  -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>회원가입</title>
</head>
<body>
<%
        //로긴한사람이라면  userID라는 변수에 해당 아이디가 담기고 그렇지 않으면 null값
        String userId = null;
        if (session.getAttribute("userId") != null) {
            userId = (String) session.getAttribute("userId");
        }
        String id = request.getParameter("userId");
        userInfoDao dao = new userInfoDao();
        userInfoDto dto = dao.userInfoView(id);
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
                //라긴안된경우]
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
    <!-- 로긴폼 -->
    <div class="container">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <!-- 점보트론 -->
            <div class="jumbotron" style="padding-top: 20px;width:400px;">
                <!-- 로그인 정보를 숨기면서 전송post -->
                <form action = "modifyUserInfo.jsp" method = "post">
                     <input type = "hidden" name = "userId" value = "<%=id %>">
                    <h3 style="text-align: center; padding-bottom: 30px;">유저 정보 수정</h3>
                                        <div class="form-group">
                        <input type="text" class="form-control" placeholder="이름" name="userName" maxlength="20" value = "<%= dto.getUserName()%>">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="전화 번호"
                            name="userPhoneNumber" maxlength="50" value = "<%=dto.getUserPhoneNumber()%>">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="이메일"
                            name="usereMail" maxlength="50" value = "<%= dto.getUsereMail()%>">
                    </div>
                    <input type = "submit" value = "수정" class="btn btn-primary">
                    <a onclick="return confirm('정말로 삭제하시겠습니까?')" href="removeUser.jsp?userId=<%=id %>" class="btn btn-primary">삭제</a>
                </form>
            </div>
        </div>
    </div>
    <!-- 애니매이션 담당 JQUERY -->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <!-- 부트스트랩 JS  -->
    <script src="js/bootstrap.js"></script>
</body>
</html>