<%--
  Created by IntelliJ IDEA.
  User: 李家兴
  Date: 2018/4/14
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" charset="utf-8" src="/js/user_login.js"></script>
    <link rel="stylesheet" type="text/css"
          href="/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript"
            src="/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"></script>
</head>
<body>
<div class="easyui-dialog" title="管理员登录" data-options="closable:false,draggable:false"
     style="width: 400px; height: 300px; padding: 10px;">
    <div style="margin-left: 50px; margin-top: 50px;">
        <div style="margin-bottom: 20px;">
            <div>
                用户名:
                <input name="username" class="easyui-textbox" data-options="required:true"
                       style="width: 200px; height: 32px"  />
            </div>
        </div>
        <div style="margin-bottom: 20px">
            <div>
                密&nbsp;&nbsp;码&nbsp;:
                <input name="password" class="easyui-textbox" type="password"
                       style="width: 200px; height: 32px" data-options="required:true"  />
            </div>
        </div>
        <div>
            <a id="login" class="easyui-linkbutton" iconCls="icon-ok" >登录</a>
            <a id="register" class="easyui-linkbutton" iconCls="icon-ok" >注册</a>
        </div>
        <script type="text/javascript">
            /* 登录 */
            $("#login").click(function() {
                var username = $("[name=username]").val();
                var password = $("[name=password]").val();

                if (username == '' || password == '') {
                    $.messager.alert('错误', "用户名或者密码为空！");
                    return;
                }

                /* ajax请求 */
                $.ajax({
                    url : "/user/login.do",
                    type : "post",
                    data : {
                        "username" : username,
                        "password" : password
                    },
                    dataType : "text",
                    success : function(data) {
                        if (data != null && data == 'index') {

                            window.location.href = "/index.jsp";

                        } else if (data != null && data == 'login') {

                            $.messager.alert('错误', "用户名或者密码错误！");
                            return;
                        }
                    }
                });

            });

            /* 注册 */
            $("#login").click(function() {
                var username = $("[name=username]").val();
                var password = $("[name=password]").val();

                if (username == '' || password == '') {
                    $.messager.alert('错误', "用户名或者密码为空！");
                    return;
                }

                /* ajax请求 */
                $.ajax({
                    url : "/user/register.do",
                    type : "post",
                    data : {
                        "username" : username,
                        "password" : password
                    },
                    dataType : "text",
                    success : function(data) {
                        if (data != null && data == 'index') {

                            window.location.href = "/index.html";

                        } else if (data != null) {

                            $.messager.alert('错误', "注册失败");
                            return;
                        }
                    }
                });

            });

        </script>
    </div>
</div>
</body>
</html>
