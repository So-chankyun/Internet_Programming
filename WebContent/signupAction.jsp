<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("EUC-KR"); %>
<%@ page import = "java.sql.Timestamp" %>
<%@ page import = "com.page.Dao.userInfoDao" %>
<jsp:useBean id = "dto" class = "com.page.Dto.userInfoDto"/>
<jsp:setProperty name = "dto" property = "*"/> 
<!-- join.jsp�� ���� �Ӽ����� name�� DtoŬ������ �ʵ���� ���ƾ� �Ѵ�. �׷� *�� ��������ν� �ڵ������� ���� ����-->
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
        alert("�̹� �����ϴ� ���̵��Դϴ�.");
        history.back();
       </script>
 <% }else{
       int ri = dao.insertUserInfo(dto);
       if(ri == userInfoDao.MEMBER_JOIN_SUCCESS){
        session.setAttribute("userId", dto.getUserId());   
%>     
        <script>
        alert("ȸ�������� �����մϴ�.");
        document.location.href = "Login_view.jsp";
        </script>
<%     
       }else{
%>          
        <script>
        alert("ȸ�����Կ� �����Ͽ����ϴ�.");
        document.location.href = "Login_view.jsp";
        </script>          
<%      }
    }
%>
</body>
</html>