
<%--
  Created by IntelliJ IDEA.
  User: 壹加柒
  Date: 2020/5/24
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/control.css" rel="stylesheet" type="text/css"/>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
    <script src="../../js/control.js"></script>
    <style>
        .delete{
            font-size: 12px;
        }
        td,th{
            vertical-align:middle;
            text-align: center;
        }
    </style>
</head>
<body>
    <!-- 侧栏菜单 -->
    <div class="navmenu">
        <ul class="list top">

        </ul>
        <ul class="list">
            <%--<li><a href="javascript:;">Login</a></li>
            <li><a href="#">Register</a></li>--%>
            <li><a href="../../index.html">Home</a></li>
            <li><a href="#">Control</a></li>
            <li><a href="#">Music</a></li>
            <li><a href="#" onclick="exit();">Exit</a></li>

        </ul>
    </div>
    <!-- 头部导航 -->
    <header class="header">
        <div class="menu">
            <div class="menu_icon "><img src="../../pic/icon-hamburger.svg"></div>
        </div>
    </header>


    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <%--header--%>
                <div class="page-header">
                    <h1>
                        <small>用户管理中心</small>
                    </h1>
                </div>
            </div>
        </div>
        <%--主要内容--%>
        <div class="row">
            <div class="col-md-2 column col-xs-2">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/toUpdateInfo">修改信息</a>
            </div>
            <div class="col-md-2 column col-xs-2">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/tlike/allLike">回到首页</a>
            </div>
            <div class="col-md-2 column col-xs-2">
            </div>
            <div class="col-md-2 column col-xs-2" >
                <div style="height: 34px; padding: 8px;text-align: center;" class="alert alert-info" role="alert" >${msg}</div>
            </div>
            <div class="col-md-4 column col-xs-4" style="float: right">
                <%--查询--%>
                <form class="form-inline" action="/user/queryTeds" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="title" id="searchTed" placeholder="请输入要搜索的内容">
                    </div>
                    <button type="submit" id="submit" class="btn btn-default">Search</button>
                </form>

            </div>
        </div>
        <%--收藏的内容--%>
        <div class="row clearfix">
            <div class="col-md-12 column" >
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>内容标题</th>
                        <th>详细介绍</th>
                        <th>观看地址</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${list != null}">
                        <c:forEach var="ted" items="${list}">
                            <tr>
                                <td style="vertical-align: middle;">${ted.tid}</td>
                                <td style="vertical-align: middle;">${ted.title}</td>
                                <td style="vertical-align: middle;">${ted.discription}</td>
                                <td style="vertical-align: middle;">${ted.mp4ShdUrl}</td>
                                <td class="unlike">
                                    <a  href="${pageContext.request.contextPath}/tlike/removeLike2?tid=${ted.tid}" class="btn btn-default btn-lg delete" role="button">取消收藏</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${list_query != null}">
                        <c:forEach var="ted" items="${list_query}">
                            <tr>
                                <td style="vertical-align: middle;">${ted.tid}</td>
                                <td style="vertical-align: middle;">${ted.title}</td>
                                <td style="vertical-align: middle;">${ted.discription}</td>
                                <td style="vertical-align: middle;">${ted.mp4ShdUrl}</td>
                                <td class="unlike">
                                    <a  href="${pageContext.request.contextPath}/tlike/addLike2?tid=${ted.tid}" class="btn btn-default btn-lg delete disabled" role="button" >点击收藏</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- 全屏变暗 -->
    <div class="changeBg"></div>

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.0/jquery.min.js"></script>

    <script>
        //取消收藏
        $(function () {
            $("#submit").click(function () {
                $(".unlike").html(" ");
            })
        });
        //退出
        function exit() {
            $.get("/user/exit",{},function (flag) {
                if (flag){
                    alert("退出成功！");
                }else {
                    alert("您尚未登录！");
                    location.href = "login.html";
                }
            })
        }
    </script>
</body>
</html>
