<%--
  Created by IntelliJ IDEA.
  User: 壹加柒
  Date: 2020/5/24
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改个人信息</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">/
    <jsp:useBean id="user" class="cn.fishei.bean.User" scope="session"/>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改个人信息</small>
                </h1>
            </div>
        </div>
    </div>
    <%--修改个人信息--%>
    <form action="${pageContext.request.contextPath}/user/updateInfo" method="post">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" name="username" class="form-control" id="username" disabled value="${user.username}" required>
        </div>
        <%--<div class="form-group">
            <label for="bookCounts">旧密码</label>
            <input type="text" name="oldPassword" class="form-control" id="bookCounts" required>
        </div>--%>
        <div class="form-group">
            <label for="newPwd">新密码</label>
            <input type="password" name="password" class="form-control" id="newPwd" required>
        </div>
        <div>
            <input class="btn btn-default" type="submit" value="Submit">
        </div>
    </form>
</div>


</body>
</html>
