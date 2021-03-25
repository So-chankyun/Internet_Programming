<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("EUC-KR"); %>
<%@ page import = "java.sql.Timestamp" %>
<%@ page import = "com.page.Dao.userInfoDao" %>
<jsp:useBean id = "dto" class = "com.page.Dto.userInfoDto"/>
<jsp:setProperty name = "dto" property = "*"/> 
<!-- join.jsp에 속한 속성들의 name과 Dto클래스의 필드명이 같아야 한다. 그럼 *을 사용함으로써 자동적으로 설정 가능-->
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
   dto.setUserRdate(new Timestamp(System.currentTimeMillis()));
   userInfoDao dao = new userInfoDao();
   if(dao.confirmId(dto.getUserId())== userInfoDao.MEMBER_EXISTENT){%>
       <script>
        alert("이미 존재하는 아이디입니다.");
        history.back();
       </script>
 <% }else{
       int ri = dao.insertUserInfo(dto);
       if(ri == userInfoDao.MEMBER_JOIN_SUCCESS){
        session.setAttribute("userId", dto.getUserId());   
%>     
        <script>
        alert("회원가입을 축하합니다.");
        document.location.href = "Login_view.jsp";
        </script>
<%     
       }else{
%>          
        <script>
        alert("회원가입에 실패하였습니다.");
        document.location.href = "Login_view.jsp";
        </script>          
<%      }
    }
%>
</body>
</html>