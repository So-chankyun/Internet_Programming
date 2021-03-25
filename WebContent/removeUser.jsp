<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "com.page.Dao.userInfoDao" %>
<%@ page import = "com.page.Dto.userInfoDto" %>
<!DOCTYPE html>
<html>
<head>
<%
userInfoDao dao = new userInfoDao();
String id = request.getParameter("userId");
dao.delete(id);
response.sendRedirect("userAdmin.jsp");
%>
<title></title>
</head>
<body>

</body>
</html>