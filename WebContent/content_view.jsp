<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<head>
<!-- ����Ʈ -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- ��Ÿ�Ͻ�Ʈ ����  -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>�����Խ���</title>
</head>
<body>
<%
        //�α��ѻ���̶��  userID��� ������ �ش� ���̵� ���� �׷��� ������ null��
        String userId = null;
        if (session.getAttribute("userId") != null) {
            userId = (String) session.getAttribute("userId");
        }
        int pageNumber = 1; //�⺻ ������ �ѹ�
        //�������ѹ����� ������
        if (request.getParameter("pageNumber") != null) {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
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
        <div class="row">
                <table class="table table-striped"
                    style="text-align: center; border: 1px solid #dddddd">
                   <thead>
                        <tr>
                            <th colspan="3"
                                style="background-color: #eeeeee; text-align: center;">�����Խ��� ��</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="width: 20%;">�� �� </td>
                            <td colspan="2">${content_view.bTitle}</td>
                        </tr>
                        <tr>
                            <td>�� �� ��</td>    
                            <td colspan="2">${content_view.bUserId}</td>
                        </tr>
                        <tr>
                            <td>�� �� ��</td>    
                            <td colspan="2">${content_view.bDate}</td>
                        </tr>
                        <tr>
                            <td>�� ��</td> 
                        </tr>
                        <tr >
                            <td colspan="3" style="min-height: 200px; text-align: left;">
                            <textarea class="form-control" style = "width : 1200px;height : 300px;padding-top: 10px;" disabled>
                                ${content_view.bContent}
                            </textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>    
                <a href = "freeboard_view.do" class="btn btn-primary">���</a>          
                    <%
                //���ۼ��� �����Ͻ� ���� ���� ����
                    if(userId.equals(session.getAttribute("contentId")) || userId.equals("admin")){
                    %>
                        <a href="modify_view.do?bId=${content_view.bId}" class="btn btn-primary">����</a>
                        <a onclick="return confirm('������ �����Ͻðڽ��ϱ�?')" href="delete.do?bId=${content_view.bId}" class="btn btn-primary">����</a>
                    <%                  
                    }
                    %>
        </div>
    </div>
    <!-- �ִϸ��̼� ��� JQUERY -->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <!-- ��Ʈ��Ʈ�� JS  -->
    <script src="js/bootstrap.js"></script>
</body>
</html>