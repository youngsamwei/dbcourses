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
<button onclick="doaudit()">审核测试</button>
<table id="tabtask"></table>
<div id="dlg_aduit_add" class="easyui-dialog"
         style="width: 300px;height:200px;padding: 10px 20px;"
     closed="true" maximizable="false" closable="false" buttons="#dlg_aduit_buttons" data-options="modal:true">
    <p>内容</p>
    <textarea id="pointname" readonly="true" rows="1" cols="20">
    </textarea>
    <input id="mainid" class="easyui-textbox" hidden="true">
    <input id="taskid" class="easyui-textbox" hidden="true">
    <a href="javascript:dotheKAudit()" class="easyui-linkbutton"  id="aduitButton"
       iconCls="icon-ok">确定</a> <a href="javascript:closeAduitwindow()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


<script type="text/javascript">
    $(function () {
        $('#tabtask').datagrid({
            title: '任务审核',
            iconCls: 'icon-edit',
            rownumbers: true,
            pagination: true,
            width: 800,
            height: 550,
            singleSelect: false,
            idField: 'id',
            url: '/task/list.do',
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: 'userId', hidden: true},
                {field: 'mainid', title: 'mainid', hidden: true},
                {field: 'type', title: '类型', width: 150,
                    formatter: function (value, row) {
                        var str='';
                        var a=row.type/10;
                        var b=row.type%10;
                        if (a==1) {
                            str='知识点';
                        }
                        else if(a==2){
                            str='段落';
                        }
                        if(b==1){
                            str=str+'增加';
                        }
                        if(b==2){
                            str=str+'编辑';
                        }
                        if(b==3){
                            str=str+'刪除';
                        }
                        return str;
                    }
                },
                {field: 'status', title: '状态', width: 150,hidden:true},
                {field: 'submitter', title: '提交人', width: 150,  editor: 'numberbox'},
                {field: 'approver', title: '审核人', width: 250, editor: 'text'},
                {
                    field: 'action', title: '审核', width: 150, align: 'center',
                    formatter: function (value, row) {
                        if (row.status=='0') {
                            var e = '<a href="#" onclick="audit(this)">审核</a> ';
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
    function dotheKAudit() {
        var themainid =$("#mainid").val();
        var theid =$("#taskid").val();
        $.ajax({
            type: "POST",
            url: "/task/auditaddkonw.do",
            data: {
                mainid : themainid,
                taskid:  theid
            },
            dataType: "json",
            success: function () {

                alert("审核成功");
                closeAduitwindow();
                $('#tabtask').datagrid('reload');
            },
            error:function () {
                alert("失败");
                closeAduitwindow();

            }
        })
    }


    function audit(target) {
        $("#tabtask").datagrid("selectRow", getRowIndex(target));
        var row = $("#tabtask").datagrid("getSelected");
        if (row) {
            $.ajax({
                type: "POST",
                url: "/knowledgepoint/audit.do",
                data: {id : row.mainid},
                dataType: "json",
                success: function (know) {
                    $("#dlg_aduit_add").dialog(
                        {
                            onOpen: function () {
                                $("#pointname").val(know.knowledgepointName);
                                $("#mainid").val(row.mainid);
                                $("#taskid").val(row.id);
                            }
                        }

                    );
                    $("#dlg_aduit_add").dialog("open").dialog("setTitle", '审核');
                }
            })
        }
    }
    function closeAduitwindow() {
        $("#dlg_aduit_add").dialog("close");
    }

    function doaudit() {
        $.ajax({
            type: "post",
            url: "/task/auditaddkonw.do",
            data: {
            },
            datatype: "json",
            success: function () {
                alert("审核成功");
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
</script>



</body>
</html>
