<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
 <!-- 로긴폼 -->
 <div class = "container">
 <figure>
    <img src="Product/${product_detail_view.oriFile}" class = "rounded">
    <figcaption>판매자 : ${product_detail_view.userId}<br>
                                    연락처 : ${product_detail_view.pPhoneNumber}
    </figcaption>
 </figure>
</div>
<div class="container">
        <div class="row">
                <table class="table table-striped" style="width : 500px; text-align: center; border: 1px solid #dddddd">
                    <tbody>
                        <tr>
                            <td style="width: 20%;">기종 </td>
                            <td>${product_detail_view.pName}</td>
                        </tr>
                        <tr>
                            <td>통신사</td>    
                            <td>${product_detail_view.pTTC}</td>
                        </tr>
                        <tr>
                            <td>가격</td>    
                            <td>${product_detail_view.pPrice}</td>
                        </tr>
                         <tr>
                            <td>판매방법</td>    
                            <c:if test="${product_detail_view.pTradeMethod == 'delivery'}">
                            <td>택배</td>
                            </c:if>
                            <c:if test="${product_detail_view.pTradeMethod == 'direct'}">
                            <td>직거래</td>
                            </c:if>
                        </tr>
                    </tbody>
                </table>
                <%
                //글작성자 본인일시 수정 삭제 가능
                    if(userId.equals(session.getAttribute("contentId")) || userId.equals("admin")){
                    %>
                        <a href="modify.pdo?pId=${product_detail_view.pId}" class="btn btn-primary">수정</a>
                        <a onclick="return confirm('정말로 삭제하시겠습니까?')" href="delete.pdo?pId=${product_detail_view.pId}" class="btn btn-primary">삭제</a>
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