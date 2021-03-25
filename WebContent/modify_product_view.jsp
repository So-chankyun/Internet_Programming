<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "com.page.Dao.PDao" %>
<%@ page import = "com.page.Dto.PDto" %>
<!DOCTYPE html>
<html>
<!-- ����Ʈ -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- ��Ÿ�Ͻ�Ʈ ����  -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>�����Խ���</title>
</head>
<body>
<%
        //�α��ѻ���̶��  userID��� ������ �ش� ���̵� ���� �׷��� ������ null��
        String pId = request.getParameter("pId");
        System.out.println(pId+"in modify_product_view");
        PDao dao = new PDao();
        PDto dto = dao.contentView(pId);
        
        String userId = null;
        if (session.getAttribute("userId") != null) {
            userId = (String) session.getAttribute("userId");
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
 <div class="container">
        <div class="row">
            <form action = "PMP" method = "post" enctype = "multipart/form-data">
            <input type = "hidden" name = "pId" value = "<%=pId %>">
                <table class="table table-striped"
                    style="text-align: center; border: 1px solid #dddddd">
                    <thead>
                        <tr>
                            <th colspan="2"
                                style="background-color: #eeeeee; text-align: center;">�Ź� ���� ����</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" class="form-control" placeholder="����" name="pName" maxlength="30" value = "<%= dto.getpName()%>"/></td>
                        </tr>
                         <tr>
                         <tr>
                            <td><input type="text" class="form-control" placeholder="��Ż�" name="pTTC" maxlength="30" value = "<%= dto.getpTTC()%>"/></td>
                        </tr>
                            <td><input type="text" class="form-control" placeholder="����" name="pPrice" maxlength="30" value = "<%=dto.getpPrice()%>"/></td>
                        </tr>
                         <tr>
                            <td>
                            <div class="form-group" style="text-align: center;">
                                <div class="btn-group" data-toggle="buttons">
                                    <label class="btn btn-primary active"> 
                                        <input type="radio" name="pTradeMethod" autocomplete="off" value="delivery" checked>�ù�
                                    </label> 
                                    <label class="btn btn-primary">
                                         <input type="radio" name="pTradeMethod" autocomplete="off" value="direct" >���ŷ�
                                    </label>
                                 </div>
                            </div>
                            </td>
                        </tr>
                         <tr>
                            <td><input type = "file" class="form-control" name="pImage"></td>
                        </tr>
                         <tr>
                            <td><input type="text" class="form-control" placeholder="�޴��� ��ȣ" name="pPhoneNumber" maxlength="30" value = "<%= dto.getpPhoneNumber()%>"/></td>
                        </tr>
                    </tbody>
                </table>  
                <input type="submit" class="btn btn-primary pull-right" value="����">
            </form>
        </div>
    </div>
        <!-- �ִϸ��̼� ��� JQUERY -->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <!-- ��Ʈ��Ʈ�� JS  -->
    <script src="js/bootstrap.js"></script>
</body>
</html>