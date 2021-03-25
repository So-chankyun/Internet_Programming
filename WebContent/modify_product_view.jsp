<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "com.page.Dao.PDao" %>
<%@ page import = "com.page.Dto.PDto" %>
<!DOCTYPE html>
<html>
<!-- 뷰포트 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 스타일시트 참조  -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>자유게시판</title>
</head>
<body>
<%
        //로긴한사람이라면  userID라는 변수에 해당 아이디가 담기고 그렇지 않으면 null값
        String pId = request.getParameter("pId");
        System.out.println(pId+"in modify_product_view");
        PDao dao = new PDao();
        PDto dto = dao.contentView(pId);
        
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
 <div class="container">
        <div class="row">
            <form action = "PMP" method = "post" enctype = "multipart/form-data">
            <input type = "hidden" name = "pId" value = "<%=pId %>">
                <table class="table table-striped"
                    style="text-align: center; border: 1px solid #dddddd">
                    <thead>
                        <tr>
                            <th colspan="2"
                                style="background-color: #eeeeee; text-align: center;">매물 정보 수정</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" class="form-control" placeholder="기종" name="pName" maxlength="30" value = "<%= dto.getpName()%>"/></td>
                        </tr>
                         <tr>
                         <tr>
                            <td><input type="text" class="form-control" placeholder="통신사" name="pTTC" maxlength="30" value = "<%= dto.getpTTC()%>"/></td>
                        </tr>
                            <td><input type="text" class="form-control" placeholder="가격" name="pPrice" maxlength="30" value = "<%=dto.getpPrice()%>"/></td>
                        </tr>
                         <tr>
                            <td>
                            <div class="form-group" style="text-align: center;">
                                <div class="btn-group" data-toggle="buttons">
                                    <label class="btn btn-primary active"> 
                                        <input type="radio" name="pTradeMethod" autocomplete="off" value="delivery" checked>택배
                                    </label> 
                                    <label class="btn btn-primary">
                                         <input type="radio" name="pTradeMethod" autocomplete="off" value="direct" >직거래
                                    </label>
                                 </div>
                            </div>
                            </td>
                        </tr>
                         <tr>
                            <td><input type = "file" class="form-control" name="pImage"></td>
                        </tr>
                         <tr>
                            <td><input type="text" class="form-control" placeholder="휴대폰 번호" name="pPhoneNumber" maxlength="30" value = "<%= dto.getpPhoneNumber()%>"/></td>
                        </tr>
                    </tbody>
                </table>  
                <input type="submit" class="btn btn-primary pull-right" value="저장">
            </form>
        </div>
    </div>
        <!-- 애니매이션 담당 JQUERY -->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <!-- 부트스트랩 JS  -->
    <script src="js/bootstrap.js"></script>
</body>
</html>