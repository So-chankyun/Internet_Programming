<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "com.page.Dao.userInfoDao" %>
<%@ page import = "com.page.Dto.userInfoDto" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<head>
<!-- ����Ʈ -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- ��Ÿ�Ͻ�Ʈ ����  -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>���� ����</title>
</head>
<body>
<%
        //�α��ѻ���̶��  userID��� ������ �ش� ���̵� ���� �׷��� ������ null��
        String userId = null;
        if (session.getAttribute("userId") != null) {
            userId = (String) session.getAttribute("userId");
        }
        
        userInfoDao dao = new userInfoDao();
        ArrayList<userInfoDto> users = dao.displayInfo();
%>
<!-- �׺���̼�  -->
       <nav class="navbar navbar-default">
    
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                aria-expaned="false">
                <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main.pdo">��ȭ�ο� �߰���</a>
        </div>
        <div class="collapse navbar-collapse"
            id="#bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="main.pdo">����</a></li>
                <li><a href="freeboard_view.do">���� �Խ���</a></li>
                <li><a href="qanda_view.qdo">Q&#38;A</a></li>
            </ul>
            <%
                //���ȵȰ��
                if (session.getAttribute("logon") == null){
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                    data-toggle="dropdown" role="button" aria-haspopup="true"
                    aria-expanded="false">�����ϱ�<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="active"><a href="Login_view.jsp">�α���</a></li>
                        <li><a href="signup_view.jsp">ȸ������</a></li>
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
                        <li><a href="userAdmin.jsp">���� ���� ����</a></li>
                    <% }%>
                        <li><a href="logoutAction.jsp">�α׾ƿ�</a></li>
                        </ul>
                    </li>
            </ul>
            <%
                }
            %>
        </div>
    </nav>
    <!-- �Խ��� -->
 <div class="container">
        <div class = "row">
            <table class="table table-striped" style="text-align:center; border:1px solid #dddddd"> 
                <thead>
                    <tr>
                        <th style="background-color: #eeeeee; text-align: center; width:15px">�̸�</th>
                        <th style="background-color: #eeeeee; text-align: center; width:15px">����</th>
                        <th style="background-color: #eeeeee; text-align: center; width:30px">��ȣ</th>
                        <th style="background-color: #eeeeee; text-align: center; width:50px">�̸���</th>
                    </tr>
                </thead>
                <tbody>
                <%
                for(int i=0;i<users.size();i++){
                %>
                <tr>
                
                <td>
                <input type = "hidden" name = "userId" value = "<%= users.get(i).getUserId()%>">
                <a href = "userInfoMD.jsp?userId=<%= users.get(i).getUserId()%>"><%= users.get(i).getUserName() %></a>
                </td>
                <td><%= users.get(i).getUserGender() %></td>
                <td><%= users.get(i).getUserPhoneNumber() %></td>
                <td><%= users.get(i).getUsereMail() %></td>
                <tr>
                <%
                }
                %>
                </tbody>
            </table>    
            <!-- ������ �ѱ�� -->
             <div class = "container" style = "text-align: center;">
                 <ul class = "pagination">  
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">����</a></li>
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">1</a></li>
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">2</a></li>
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">3</a></li>
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">4</a></li>
                     <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">5</a></li>
                    <li class = "page-item disabled"><a class = "pagelink" href = "freeboard_view.jsp">����</a></li>
                 </ul>
            </div>
        </div>
    </div>
    <!-- �ִϸ��̼� ��� JQUERY -->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <!-- ��Ʈ��Ʈ�� JS  -->
    <script src="js/bootstrap.js"></script>
</body>
</html>