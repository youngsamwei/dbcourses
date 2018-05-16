<%--
  Created by IntelliJ IDEA.
  User: 李家兴
  Date: 2018/5/15
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任务审核</title>
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
</head>
<body>

<table id="tabtask"></table>

<div id="dlg_aduit_add" class="easyui-dialog"
     style="width: 400px;height:500px;padding: 10px 20px;"
     closed="true" maximizable="false" closable="false" buttons="#dlg_aduit_buttons" data-options="modal:true">
    <p>内容</p>
    <textarea rows="3" cols="20">
     在w3school，你可以找到你所需要的所有的网站建设教程。
    </textarea>
    <br>
        <a href="javascript:closeAduitwindow()" class="easyui-linkbutton"  id="aduitButton"
           iconCls="icon-ok">确定</a> <a href="javascript:closeAduitwindow()"
                                       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>

</div>

<script type="text/javascript">
    $(function () {
        $('#tabtask').datagrid({
            title: '任务审核',
            iconCls: 'icon-edit',
            rownumbers: true,
            pagination: true,
            width: 1100,
            height: 550,
            singleSelect: false,
            idField: 'id',
            url: '/task/list.do',
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: 'userId', hidden: true},
                {field: 'mainid', title: 'mainid', hidden: true},
                {field: 'type', title: '类型', width: 150},
                {field: 'status', title: '状态', width: 150,},
                {field: 'submitter', title: '提交人', width: 150, align: 'right', editor: 'numberbox'},
                {field: 'approver', title: '审核人', width: 250, editor: 'text'},
                {
                    field: 'action', title: '审核', width: 150, align: 'center',
                    formatter: function (value, row) {
                        if (row.status=='0') {
                            var e = '<a href="#" onclick="aduit(this)">审核</a> ';
                            return e;
                        }
                        else
                        {
                            var s ='已审核'
                            return s;
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



    function aduit(target) {
        $("#tabtask").datagrid("selectRow", getRowIndex(target));
        var row = $("#tabtask").datagrid("getSelected");
        if (row) {
//            $.ajax({
//                type: "POST",
//                url: "/user/selectbyid.do",
//                data: {userId: row.userId},
//                dataType: "json",
//                success: function (user) {
//                    $("#dlg_edit_userinfo").dialog(
//                        {
//                            onOpen: function () {
//                                var mypower = new Array()
//                                if (user.power == 3) {
//
//                                    $('#input_power').combobox('setValues', '1,2'.split(','));
//                                }
//                                else if (user.power == 5) {
//                                    $('#input_power').combobox('setValues', '1,4'.split(','));
//                                }
//                                else if (user.power == 6) {
//                                    $('#input_power').combobox('setValues', '2,4'.split(','));
//                                }
//                                else if (user.power == 7) {
//                                    $('#input_power').combobox('setValues', '1,2,4'.split(','));
//                                }
//                                else {
//                                    $('#input_power').combobox('setValue', user.power);
//                                }
//
//                                $("#input_userName").val(user.userName);
//                                $("#input_nickName").val(user.nickName);
//                                $("#input_remark").val(user.remark);
//                                $("#input_userId").val(row.userId);
//                                $('#input_userGroup').combobox('setValue', user.userGroup);
//
//                            }
//                        });
//
//
//                }
//            })
            $("#dlg_aduit_add").dialog("open").dialog("setTitle", '审核');
        }
    }
    function closeAduitwindow() {
        $("#dlg_aduit_add").dialog("close");
    }
</script>



</body>
</html>
