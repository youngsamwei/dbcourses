<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>微信登录</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <style type="text/css">
        body{
            background:url("images/background.jpg");
            background-repeat: no-repeat;
            /*background-position: center top; */
            background-size:1368px 768px;
            background-position-x:50%;
            /*background-size: cover;*/
        }
        button{
            position:relative;
            margin-left: 18%;
            margin-top: 100%;
        }
    </style>
</head>
<body>

<!-- 你的HTML代码 -->

<script src="../layui/layui.js"></script>

<form class="layui-form" action="/wxChatServlet.do" method="get">
    <div ><img src="images/erweima.png"></div>
    <div class="layui-form-item">

        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">微信登录</button>
        </div>
    </div>
</form>
</body>
</html>