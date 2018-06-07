<%--
  Created by IntelliJ IDEA.
  User: 李家兴
  Date: 2018/4/16
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css"
          href="/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript"
            src="/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript"
            src="/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css"
          href="/font-awesome-4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="/css/knowledgepoint.css">
    <script type="text/javascript" charset="utf-8" src="/js/user_load.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/user_search.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/user_manager.js"></script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
<%
    if (session.getAttribute("user") != null && session.getAttribute("power") != null) {
        int userpower = 0;
        userpower = Integer.parseInt(session.getAttribute("power").toString());
        if (userpower == 9) {
%>
<div style="margin-bottom:20px">
    <%--<a href="#" onclick="getSelected()">GetSelected</a>--%>
    <%--<a href="#" onclick="getSelections()">GetSelections</a>--%>
</div>
<div style="margin:20px 0;"></div>

<script>
    function getEditrow(){
        var userId= $('#input_userId').val();
        var userName= $('#input_userName').val();
        var nickName =$('#input_nickName').val();
        var remark =$('#input_remark').val();
        var group= $('#input_userGroup').combobox('getText');

        var user ={
            userId:userId,
            userName:userName,
            userGroup:group,
            nickName:nickName,
            remark:remark
        };
        $.ajax({
            type: "post",
            url: "/user/update.do",
            data: { user: JSON.stringify(user)},//非常重要的一步
            datatype: "json",
            success: function () {
                alert("编辑成功");
                doSearchUser();
                $("#dlg_edit_user").dialog("close");
            },
            error:function () {
                alert("编辑失败");
                doSearchUser();
            }

        });
    }
    function  doSearchUser(){
        var userName =$("#userName").val();
        var nickName =$("#nickName").val();
        var userGroup= $('#userGroup').combobox('getValue');
        if(userGroup=='')
        {
            userGroup='0';
        }
        $('#tuser').datagrid('reload',{
            userName:userName,
            nickName:nickName,
            userGroup:userGroup
        })
    }


    $(function () {
        $('#tuser').datagrid({
            title: '用户管理',
            iconCls: 'icon-edit',
            rownumbers: true,
            pagination: true,
            width: 1000,
            height: 500,
            striped:true,
            resizeHandle:'both',
            fitColumns:true,
            singleSelect: false,
            idField: 'userId',
            url: '/user/init.do',
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: 'userId', hidden: true},
                {field: 'userGroup', title: '分组', width: 150},
                {
                    field: 'userName',
                    title: '账号',
                    width: 150,
                    align: 'right',
                },
                {field: 'nickName', title: '昵称', width: 150, align: 'right', editor: 'numberbox'},
                {field: 'remark', title: '备注', width: 250, editor: 'text'},
                {
                    field: 'action', title: 'Action', width: 150, align: 'center',
                    formatter: function () {
                        var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
                        return e ;
                    }
                }
            ]],

            onBeforeEdit: function (index, row) {
                row.editing = true;
                updateActions(index);
            },
            onAfterEdit: function (index, row) {
                row.editing = false;
                updateActions(index);
            },
            onCancelEdit: function (index, row) {
                row.editing = false;
                updateActions(index);
            }
        });
    });
    function editrow(target) {
        $("#tuser").datagrid("selectRow", getRowIndex(target));
        var row = $("#tuser").datagrid("getSelected");
        if (row) {
            $.ajax({
                type: "POST",
                url: "/user/selectbyid.do",
                data: {userId: row.userId},
                dataType: "json",
                success: function (user) {
                    $("#dlg_edit_userinfo").dialog(
                        {
                            onOpen: function () {
                                $("#input_userName").val(user.userName);
                                $("#input_nickName").val(user.nickName);
                                $("#input_remark").val(user.remark);
                                $("#input_userId").val(row.userId);
                                $('#input_userGroup').combobox('setValue', user.userGroup);

                            }
                        });
                    $("#dlg_edit_userinfo").dialog("open").dialog("setTitle", user.userName).window('resize',{top: 130,left:400});
                }
            })
        }
    }


    function closeUserEditDialog() {
        $("#dlg_edit_userinfo").dialog("close");
    }

</script>


<div id="dlg_edit_userinfo" class="easyui-dialog"
     style="width: 400px;height:500px;padding: 10px 20px;"
     closed="true" maximizable="false" closable="false" buttons="#dlg_edit_user_buttons" data-options="modal:true">
    <p>账号</p>
    <input id="input_userName" name="input_userName" class="easyui-textbox" disabled="true" iconCls="icon-man"
           style="width:200px"></input>
    <p>昵称</p>
    <input id="input_nickName" name="input_nickName" class="easyui-textbox" data-options="iconCls:'icon-user'"
            style="width:200px"></input>
    <p>分组</p>
    <input id="input_userGroup" name="input_userGroup" class="easyui-combobox"  style="width:200px"
           data-options="
                              url: '/group/list.do',
                              method: 'get',
                              valueField: 'id',
                              textField: 'name',
                              multiple:false,
                              panelHeight:'auto'"></input>
    <p>备注</p>
    <input id="input_remark" name="input_remark" class="easyui-textbox" style="height:100px; width:200px"
            data-options="multiline:true"></input>
    <input id="input_userId" name="input_userId" class="easyui-textbox" style="width:200px" hidden="true"></input>
    <br>
    <br>
    <br>
    <div id="dlg_edit_user_buttons">
        <a href="javascript:getEditrow()" class="easyui-linkbutton"  id="editButton"
           iconCls="icon-ok">确定</a> <a href="javascript:closeUserEditDialog()"
                                       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
</div>
    <div class="wu-toolbar-button">

            <span>账号:</span>
            <input id="userName" class="easyui-textbox">
            <span>昵称:</span>
            <input id="nickName" class="easyui-textbox">
            分组: <input id="userGroup" name="userGroup" class="easyui-combobox" style="width:200px" data-options="
                              url: '/group/list.do',
                              method: 'get',
                              valueField: 'id',
                              textField: 'name',
                              multiple:false,
                              panelHeight:'auto'"></input>

        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchUser()" plain="true">查找</a>
        <%--<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openGroupadd()" plain="true">添加分组</a>--%>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="getDelete()" plain="true">批量删除</a>
    </div>
<table id="tuser" ></table>
<%
} else {
%>
<a href="../index.jsp">权限不足</a>
<%
    }
} else {
%>
<a href="../index.jsp">尚未登录,前往登录</a>
<%

    }
%>
</div>
</body>


</html>
