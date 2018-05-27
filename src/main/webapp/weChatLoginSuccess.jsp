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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>登录成功</title>
	<link rel="stylesheet" href="../layui/css/layui.css">
	<style type="text/css">

		button{
			position:fixed;
			left:50%; top:80%;
			transform: translate(-50%,-50%);
			width:200px;height:40px;
			/*background-color: #00a2d4;*/
		}
		.txt{
			position:fixed;
			left:50%; top:70%;
			transform: translate(-50%,-50%);
		}
	</style>
</head>
<span style="front-size:18px;"><style type="text/css">
	.bk{
		background-image: url("images/wxloginsuccess.jpg");
		background-repeat: no-repeat;
		background-position: center top;
	}
</style>
	<body class="bk" >

<script src="../layui/layui.js"></script>
<form class="layui-form" action="index.jsp" method="post" style="margin-top: 30%;">
	<div class="txt">
		<a>恭喜您,</a>
		<a>${name}</a>
	</div>
	<div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">登录成功</button>
        </div>
    </div>
</form>
</body>
</span>
</html>