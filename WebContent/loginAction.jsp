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
    alert("���̵� �������� �ʽ��ϴ�.");
    history.back();
    </script>
<%
   }else if(checkNum == 0){
%> 
    <script>
    alert("��й�ȣ�� Ʋ���ϴ�.");
    history.back();
    </script>      
  <%
     }else if(checkNum == 1){ // �α����� ���� �ߴµ� dto�� ��� �������� �ִ°�?
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