<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "com.page.Dao.userInfoDao" %>
<%@ page import = "com.page.Dto.userInfoDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<%
userInfoDao dao = new userInfoDao();
String id = request.getParameter("userId");
System.out.println(id+" in the modify");
String userName = request.getParameter("userName");
String userPhoneNumber = request.getParameter("userPhoneNumber");
String usereMail = request.getParameter("usereMail");
dao.modify(id, userName, userPhoneNumber, usereMail);
response.sendRedirect("userAdmin.jsp");
%>
<title>Insert title here</title>
</head>
<body>

</body>
</html>