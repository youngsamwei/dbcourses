<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" >
<html>
<head>
    <base href="<%=basePath%>" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>请先绑定用户</title>
    <link rel="stylesheet" href="../layui/css/layui.css">
    <style type="text/css">
        button{
            /*position:absolute;*/
            position:fixed;
            left:50%;
            /*top:50%;*/
            transform: translate(-50%,-50%);
            width:200px;height:40px;
            /*padding-top: 200px;*/
            margin-top: 80px;
            /*background-color: #00a2d4;*/
        }

    </style>
</head>
<span style="front-size:18px;"><style type="text/css">
	.bk{
        background-image: url("images/wxbindlogin.jpg");
        background-repeat: no-repeat;
        background-position: center top;
    }
</style>
	<body class="bk" >

<script src="../layui/layui.js"></script>

<form class="layui-form" action="bindUser.do" method="post" style="margin-top: 30%;">
	 <div class="layui-form-item">
    <label class="layui-form-label">账号</label>
    <div class="layui-input-block">
      <input type="text" name="account" required  lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input"  style="width:200px;" >
        <input type="hidden"  name="openid"  value="${openid }">
    </div>
  </div>
    <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-block">
      <input type="text" name="password" required  lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input"  style="width:200px;" >
    </div>
  </div>
	<div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">绑定并登录</button>
        </div>
    </div>
</form>
</body>
</span>
</html>