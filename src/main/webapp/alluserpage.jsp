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
            src="/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>

    <link rel="stylesheet" type="text/css"
          href="/font-awesome-4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="/css/knowledgepoint.css">
    <script type="text/javascript" charset="utf-8" src="/js/user_load.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/user_search.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/knowledgepoint_manager.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/knowledgepoint_search.js"></script>

</head>
<body>
<%
    if (session.getAttribute("user") != null && session.getAttribute("power") != null) {
        int userpower = 0;
        userpower = Integer.parseInt(session.getAttribute("power").toString());
        if (userpower == 9) {
%>
<div style="margin-bottom:20px">
    <%--<a href="#" onclick="getSelected()">GetSelected</a>--%>
    <%--<a href="#" onclick="getSelections()">GetSelections</a>--%>
    <a href="#" onclick="getDelete()">批量删除</a>
</div>
<div style="margin:20px 0;"></div>

<script>
    function openGroupadd() {
        $("#dlg_add_group").dialog("open").dialog("setTitle", '输入组名');
    }
    function closeGroupadd() {
        $("#dlg_add_group").dialog("close");
    }
    function doaddGroup() {

        var groupname =$("#groupname").val();
        $.ajax({
            type: "post",
            url: "/group/add.do",
            data: {
                name:groupname
            },
            datatype: "json",
            success: function () {
                alert("添加成功");
                //doSearchUser();
                //$('#editButton').linkbutton({disabled:true});

            },
            error:function () {
                alert("失败");
                //  doSearchUser();
                // $('#editButton').linkbutton({disabled:true});
            }

        });
    }



    $(function () {
        $('#tt').datagrid({
            title: '用户管理',
            iconCls: 'icon-edit',
            rownumbers: true,
            pagination: true,
            width: 1000,
            height: 550,
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
                    align: 'right'
                },
                {field: 'nickName', title: '昵称', width: 150, align: 'right', editor: 'numberbox'},
                {field: 'remark', title: '备注', width: 250, editor: 'text'},
                {
                    field: 'action', title: 'Action', width: 150, align: 'center',
                    formatter: function (value, row, index) {

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


</script>

<div id="tb" style="padding:3px">
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

    <button  class="easyui-linkbutton"  onclick="doSearchUser()" data-options="iconCls:'icon-search'">查找</button>
   <button class="easyui-linkbutton" onclick="openGroupadd()" data-options="iconCls:'icon-add'">添加分组</button>
</div>

<div id="dlg_add_group" class="easyui-dialog"
     style="width: 260px;height:130px;padding: 10px 20px; position: relative; z-index:1000;"
     closed="true" maximizable="false" closable="false" buttons="#dlg_add_group_buttons"  data-options="modal:true">
    <input id="groupname" name="groupname" class="easyui-textbox" style="width:200px" ></input>

    <div id="dlg_add_group_buttons">
        <a href="javascript:doaddGroup()" class="easyui-linkbutton"
           iconCls="icon-ok">确定</a> <a href="javascript:closeGroupadd()"
                                       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
</div>


<div id="dlg_edit_userinfo" class="easyui-dialog"
     style="width: 400px;height:500px;padding: 10px 20px;"
     closed="true" maximizable="false" closable="false" buttons="#dlg_edit_user_buttons" data-options="modal:true">
    <p>账号</p>
    <input id="input_userName" name="input_userName" class="easyui-textbox" disabled="true" iconCls="icon-man"
           style="width:200px"></input>
    <p>昵称</p>
    <input id="input_nickName" name="input_nickName" class="easyui-textbox" data-options="iconCls:'icon-user'"
           onchange=buttonAble() style="width:200px"></input>
    <p>分组</p>
    <input id="input_userGroup" name="input_userGroup" class="easyui-combobox" onchange=buttonAble() style="width:200px"
           data-options="
                              url: '/group/list.do',
                              method: 'get',
                              valueField: 'id',
                              textField: 'name',
                              multiple:false,
                              panelHeight:'auto'"></input>
    <p>备注</p>
    <input id="input_remark" name="input_remark" class="easyui-textbox" style="height:100px; width:200px"
           onchange=buttonAble() data-options="multiline:true"></input>
    <input id="input_userId" name="input_userId" class="easyui-textbox" style="width:200px" hidden="true"></input>
    <br>
    <br>
    <br>
    <div id="dlg_edit_user_buttons">
        <a href="javascript:getEditrow()" class="easyui-linkbutton" disabled="true" id="editButton"
           iconCls="icon-ok">确定</a> <a href="javascript:closeUserEditDialog()"
                                       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
</div>

<table id="tt" ></table>
<%
} else {
%>
<a href="index.jsp">权限不足</a>
<%
    }
} else {
%>
<a href="index.jsp">尚未登录,前往登录</a>
<%

    }
%>
</body>


</html>
