<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import ="com.page.Dao.userInfoDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<%
   request.setCharacterEncoding("EUC-KR");
   String id = request.getParameter("userId");
   String pw = request.getParameter("userPw");
   
   userInfoDao dao = new userInfoDao();
   int checkNum = dao.userCheck(id, pw);
   if(checkNum == -1){
%>
    <script>
    alert("아이디가 존재하지 않습니다.");
    history.back();
    </script>
<%
   }else if(checkNum == 0){
%> 
    <script>
    alert("비밀번호가 틀립니다.");
    history.back();
    </script>      
  <%
     }else if(checkNum == 1){ // 로그인을 성공 했는데 dto가 어떻게 없을수가 있는가?
    	   session.setAttribute("userId", id);
           session.setAttribute("logon","yes");
           dao.loginTime(id);
           response.sendRedirect("main.pdo");
     }
%>  
</head>
<body>

</body>
</html>