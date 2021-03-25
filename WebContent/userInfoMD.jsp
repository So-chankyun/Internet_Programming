<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import = "com.page.Dao.userInfoDao" %>
<%@ page import = "com.page.Dto.userInfoDto" %>
<!DOCTYPE html>
<html>
<head>
<!-- ����Ʈ -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- ��Ÿ�Ͻ�Ʈ ����  -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>ȸ������</title>
</head>
<body>
<%
        //�α��ѻ���̶��  userID��� ������ �ش� ���̵� ���� �׷��� ������ null��
        String userId = null;
        if (session.getAttribute("userId") != null) {
            userId = (String) session.getAttribute("userId");
        }
        String id = request.getParameter("userId");
        userInfoDao dao = new userInfoDao();
        userInfoDto dto = dao.userInfoView(id);
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
                //���ȵȰ��]
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
    <!-- �α��� -->
    <div class="container">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <!-- ����Ʈ�� -->
            <div class="jumbotron" style="padding-top: 20px;width:400px;">
                <!-- �α��� ������ ����鼭 ����post -->
                <form action = "modifyUserInfo.jsp" method = "post">
                     <input type = "hidden" name = "userId" value = "<%=id %>">
                    <h3 style="text-align: center; padding-bottom: 30px;">���� ���� ����</h3>
                                        <div class="form-group">
                        <input type="text" class="form-control" placeholder="�̸�" name="userName" maxlength="20" value = "<%= dto.getUserName()%>">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="��ȭ ��ȣ"
                            name="userPhoneNumber" maxlength="50" value = "<%=dto.getUserPhoneNumber()%>">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="�̸���"
                            name="usereMail" maxlength="50" value = "<%= dto.getUsereMail()%>">
                    </div>
                    <input type = "submit" value = "����" class="btn btn-primary">
                    <a onclick="return confirm('������ �����Ͻðڽ��ϱ�?')" href="removeUser.jsp?userId=<%=id %>" class="btn btn-primary">����</a>
                </form>
            </div>
        </div>
    </div>
    <!-- �ִϸ��̼� ��� JQUERY -->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <!-- ��Ʈ��Ʈ�� JS  -->
    <script src="js/bootstrap.js"></script>
</body>
</html>