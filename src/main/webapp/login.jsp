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
            <a id="login" class="easyui-linkbutton" iconCls="icon-ok" >登录</a>&emsp;&emsp;&emsp;&emsp;
            <a id="register" class="easyui-linkbutton" iconCls="icon-ok" onclick="openRegister()">注册</a>
        </div>
        <script type="text/javascript">
            $(document).ready(function(e) {
                $(this).keydown(function (e){
                    if(e.which == "13"){
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
                    }
                })
            });
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
            function toRegister() {
                var username = $("[name=input_username]").val();
                var password = $("[name=input_password]").val();
                var nickname = $("[name=input_nickname]").val();
                var password2 =$("[name=input_password2]").val();
                alert(password +"  "+password2)
                if (username == '' || password == ''||nickname=='') {
                    $.messager.alert('错误', "用户名密码,或昵称为空！");
                    return;
                }
                if (password!=password2) {
                    $.messager.alert('错误', "两次输入密码不一致！");
                    return;
                }
                $.ajax({
                    url : "/user/register.do",
                    type : "post",
                    data : {
                        "username" : username,
                        "password" : password,
                        "nickname" : nickname
                    },
                    dataType : "text",
                    success : function(data) {
                        if (data != null && data == 'index') {

                            alert("注册成功");
                            window.location.href = "/index.jsp";

                        } else if (data != null && data == 'login') {

                            $.messager.alert('错误', "注册失败");
                            return;
                        }
                    }
                });
            }
            
            
            function openRegister() {
                $("#dlg_register_add").dialog("open").dialog("setTitle", '注册');
            }
            function closeReigsterwindow() {
                $("#dlg_register_add").dialog("close");

            }
        </script>
    </div>

    <form id="registerfrom" action="/user/register.do">
    <div id="dlg_register_add" class="easyui-dialog"
         style="width: 400px;height:500px;padding: 10px 20px;"
         closed="true" maximizable="false" closable="false" buttons="#dlg_aduit_buttons" data-options="modal:true">
        <p>账号</p>
        <input name="input_username" class="easyui-textbox" iconCls="icon-man"
               style="width:200px"></input>
        <p>昵称</p>
        <input  name="input_nickname" class="easyui-textbox" data-options="iconCls:'icon-user'"
               onchange=buttonAble() style="width:200px"></input>
        <p>密&nbsp;&nbsp;码&nbsp;:</p>
        <input name="input_password" class="easyui-textbox" type="password"
                   style="width: 200px; height: 32px" data-options="required:true"  />
        <p>重复密码</p>
        <input name="input_password2" class="easyui-textbox" type="password"
               style="width: 200px; height: 32px" data-options="required:true"  />
        <div id="dlg_aduit_buttons">
            <a class="easyui-linkbutton" id="aduitButton" onclick="toRegister()"
               iconCls="icon-ok">确定</a> <a href="javascript:closeReigsterwindow()"
                                           class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
        </div>
    </div>
    </form>
</div>
</body>
</html>
