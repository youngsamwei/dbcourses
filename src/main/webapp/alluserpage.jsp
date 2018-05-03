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

<div>

    <script type="text/javascript">
        var row = $('#tt').datagrid('getChecked');
        if (row) {
            alert('Item ID:' + row.userId
            );
        }
    </script>
</div>
<script>
    $(function () {
        $('#tt').datagrid({
            title: '用户管理',
            iconCls: 'icon-edit',
            rownumbers: true,
            pagination: true,
            width: 1100,
            height: 550,
            singleSelect: false,
            idField: 'userId',
            url: '/user/init.do',
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'userId', title: 'userId', hidden: true, width: 150},
                {field: 'userGroup', title: '分组', width: 150},
                {
                    field: 'userName',
                    title: '用户名',
                    width: 150,
                    align: 'right',
                    editor: {type: 'numberbox', options: {precision: 1}}
                },
                {field: 'nickName', title: '昵称', width: 150, align: 'right', editor: 'numberbox'},
                {
                    field: 'power', title: '权限', width: 150, align: 'right'
                    ,
                    formatter: function (value) {
                        if (value == 9) {
                            return "管理";
                        }
                        else if (value == 0) {
                            return "无权限";
                        }
                        else if (value == 1) {
                            return "編輯";
                        }
                        else if (value == 2) {
                            return "增加";
                        }
                        else if (value == 3) {
                            return "增加,编辑";
                        }
                        else if (value == 4) {
                            return "删除";
                        }
                        else if (value == 5) {
                            return "删除,编辑";
                        }
                        else if (value == 6) {
                            return "删除,增加";
                        }
                        else if (value == 7) {
                            return "删除,增加,编辑";
                        }

                    }
                },

                {field: 'remark', title: '备注', width: 250, editor: 'text'},
                {
                    field: 'action', title: 'Action', width: 150, align: 'center',
                    formatter: function (value, row, index) {
                        if (row.editing) {
                            var s = '<a href="#" onclick="saverow(this)">Save</a> ';
                            var c = '<a href="#" onclick="cancelrow(this)">Cancel</a>';
                            return s + c;
                        } else {
                            var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
                            var d = '<a href="#" onclick="deleterow(this)">删除</a>';
                            return e + d;
                        }
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

    function updateActions(index) {
        $('#tt').datagrid('updateRow', {
            index: index,
            row: {}
        });
    }

    function getRowIndex(target) {
        var tr = $(target).closest('tr.datagrid-row');
        return parseInt(tr.attr('datagrid-row-index'));
    }

    function editrow(target) {
        $("#tt").datagrid("selectRow", getRowIndex(target));
        var row = $("#tt").datagrid("getSelected");
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
                                var mypower = new Array()
                                if (user.power == 3) {

                                    $('#input_power').combobox('setValues', '1,2'.split(','));
                                }
                                else if (user.power == 5) {
                                    $('#input_power').combobox('setValues', '1,4'.split(','));
                                }
                                else if (user.power == 6) {
                                    $('#input_power').combobox('setValues', '2,4'.split(','));
                                }
                                else if (user.power == 7) {
                                    $('#input_power').combobox('setValues', '1,2,4'.split(','));
                                }
                                else {
                                    $('#input_power').combobox('setValue', user.power);
                                }

                                $("#input_userName").val(user.userName);
                                $("#input_nickName").val(user.nickName);
                                $("#input_remark").val(user.remark);
                                $("#input_userId").val(row.userId);
                                $('#input_userGroup').combobox('setValue', user.userGroup);

                            }
                        });
                    $("#dlg_edit_userinfo").dialog("open").dialog("setTitle", user.userName);

                }
            })
        }
    }

    function deleterow(target) {
        $.messager.confirm('Confirm', 'Are you sure?', function (r) {
            if (r) {
                $('#tt').datagrid('deleteRow', getRowIndex(target));
            }
        });
    }

    function saverow(target) {
        $('#tt').datagrid('endEdit', getRowIndex(target));
    }

    function cancelrow(target) {
        $('#tt').datagrid('cancelEdit', getRowIndex(target));
    }

    function insert() {
        var row = $('#tt').datagrid('getSelected');
        if (row) {
            var index = $('#tt').datagrid('getRowIndex', row);
        } else {
            index = 0;
        }
        $('#tt').datagrid('insertRow', {
            index: index,
            row: {
                status: 'P'
            }
        });
        $('#tt').datagrid('selectRow', index);
        $('#tt').datagrid('beginEdit', index);
    }

</script>
</head>

<div id="tb" style="padding:3px">
    <span>用户名:</span>
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

    <a href="#" class="easyui-linkbutton" plain="true" onclick="doSearchUser()">查找</a>
</div>

<div id="dlg_edit_userinfo" class="easyui-dialog"
     style="width: 400px;height:500px;padding: 10px 20px;"
     closed="true" maximizable="false" closable="false" buttons="#dlg_edit_user_buttons" data-options="modal:true">
    <p>用户名</p>
    <input id="input_userName" name="input_userName" class="easyui-textbox" disabled="true" iconCls="icon-man"
           style="width:200px"></input>
    <p>昵称</p>
    <input id="input_nickName" name="input_nickName" class="easyui-textbox" data-options="iconCls:'icon-user'"
           onchange=buttonAble() style="width:200px"></input>
    <p>权限</p>
    <select id="input_power" class="easyui-combobox" name="dept" style="width:200px;" onchange=buttonAble()
            data-options="multiple:true">
        <option value="0">无权限</option>
        <option value="1">编辑</option>
        <option value="2">增加</option>
        <option value="4">删除</option>
        <option value="9">管理</option>
    </select>
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

<table id="tt"></table>
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
