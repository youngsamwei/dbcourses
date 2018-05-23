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
    <title>段落审核</title>
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
<div class="wu-toolbar-button">
    <span>提交人:</span>
    <input id="submitter2" class="easyui-textbox">
    <span>审核人:</span>
    <input id="approver2" class="easyui-textbox">
    类型:
    <select id="tasktype2" class="easyui-combobox" name="dept" style="width:50px;">
        <option value=null selected="true">所有</option>
        <option value="22">编辑</option>
        <option value="21">增加</option>
        <option value="23">删除</option>
    </select>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="SearchTask2()" plain="true">查找</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="getDelete()" plain="true">批量删除</a>
    <input type="checkbox" id="taskstatus2" value="0">只看未审核的任务
    <br>
    提交时间:
    <span>开始时间:</span><input class="easyui-datetimebox" id="beforetime2"
                             data-options="showSeconds:false"  style="width:150px">
    <span> 结束时间:</span><input class="easyui-datetimebox" id="aftertime2"
                              data-options="showSeconds:false"  style="width:150px">
</div>
<table id="tabtask2"></table>

<div id="dlg_aduit_edit" class="easyui-dialog"
     style="width: 700px;height:600px;padding: 10px 20px;"
     closed="true" maximizable="false" closable="false" buttons="#audit_edit_button,#audit_edit_close" data-options="modal:true">
    <input id="update_task_id" class="easyui-textbox" hidden="true">
    <input id="para_edit_id" class="easyui-textbox" hidden="true">
    <div id='editparagraph' class="editparagraph">
        <h3>编辑前:</h3>
        <div style='background: #F0F8FF'>
        <div class='content' id='thebefore' style="height: 160px"></div>
        </div>
        <br>
        <h3>编辑后:</h3>
        <div style='background: #F0F8FF'>
        <div class='content' id='theafter' style="height: 160px"></div>
        </div>
        <br>
        <br>
    </div>
    <input id="editid" class="easyui-textbox" hidden="true">
    <input id="taskid2" class="easyui-textbox" hidden="true">

    <div id="audit_edit_button">
        <a href="javascript:dothePEAudit()" class="easyui-linkbutton"
           iconCls="icon-ok">通过</a> <a href="javascript:closeAduiteditwindow()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
    <div id="audit_edit_close">
        <a href="javascript:closeAduiteditwindow()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
</div>
<div id="dlg_add_para" class="easyui-dialog"
     style="width: 700px;height:400px;padding: 10px 20px;"
     closed="true" maximizable="false" closable="false" buttons="#audit_addpara_button,#audit_addpara_close,#audit_delepara_button" data-options="modal:true">
    <div >
        <h3>知识点:</h3>
            <div class='content' id='thetitle' style="height: 20px"></div>
        <h3>段落:</h3>
        <div style='background: #F0F8FF'>
            <div class='content' id='thecontent' style="height: 160px"></div>
        </div>
        <input id="mainid2" class="easyui-textbox" hidden="true">
        <input id="taskid_para" class="easyui-textbox" hidden="true">
    </div>
    <div id="audit_addpara_button">
        <a href="javascript:dotheaddAudit('para')" class="easyui-linkbutton"
           iconCls="icon-ok">确定</a> <a href="javascript:closeAduitaddparawindow()"
                                       class="easyui-linkbutton" iconCls="icon-cancel" >取消</a>
    </div>
    <div id="audit_delepara_button">
        <a href="javascript:dothedeleteAudit()" class="easyui-linkbutton"
           iconCls="icon-ok">确定</a> <a href="javascript:closeAduitaddparawindow()"
                                       class="easyui-linkbutton" iconCls="icon-cancel" >取消</a>
    </div>
    <div id="audit_addpara_close">
        <a href="javascript:closeAduitaddparawindow()"
                                       class="easyui-linkbutton" iconCls="icon-cancel" >取消</a>
    </div>
</div>


<script type="text/javascript">
    $(function () {
        $('#tabtask2').datagrid({
            title: '任务审核',
            iconCls: 'icon-edit',
            rownumbers: true,
            pagination: true,
            width: 1200,
            height: 550,
            singleSelect: false,
            idField: 'id',
            url: '/task/listpara.do',
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: 'userId', hidden: true},
                {field: 'content' ,title: 'content',hidden:true},
                {field: 'mainid', title: 'mainid', hidden: true},
                {field: 'type', title: '类型', width: 150,
                    formatter: function (value, row) {
                        var str='';
                        var a=parseInt(row.type/10);
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
                {field: 'submitter', title: '提交人', width: 150},
                {field: 'createTime', title: '提交时间', width: 200},
                {field: 'approver', title: '审核人', width: 150},
                {field: 'approverTime', title: '审核时间', width: 200},
                {
                    field: 'action', title: '审核', width: 150, align: 'center',
                    formatter: function (value, row) {
                        if (row.status=='0') {
                            var e = '<a href="#" onclick="audits(this)">审核</a> ';
                            return e;
                        }
                        else
                        {
                            var s ='<a href="#" onclick="audits(this)">已审核</a> '
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

    function SearchTask2() {
        if($("#taskstatus2").is(":checked"))
        {
            var taskstatus=$("#taskstatus2").val();
        }
        var beforetime=$("#beforetime2").datebox('getValue');
        var aftertime=$("#aftertime2").datebox('getValue');
        var approver =$("#approver2").val();
        var submitter =$("#submitter2").val();
        var type =$('#tasktype2').combobox('getValue');
        // alert(type);
        $('#tabtask2').datagrid('reload',{
            beforetime:beforetime,
            aftertime:aftertime,
            approver:approver,
            submitter:submitter,
            type:type,
            taskstatus:taskstatus
        })

    }

    function dotheaddAudit(thetype) {
        var themainid;
        var theid;
        if(thetype=='know') {
            theid = $("#taskid").val();
            themainid = $("#mainid").val();
        }
        else if(thetype=='para')
        {
            theid =$("#taskid_para").val();
            themainid=$("#mainid2").val();
        }
        $.ajax({
            type: "POST",
            url: "/task/auditadd.do",
            data: {
                mainid : themainid,
                taskid:  theid,
                type: thetype
            },
            dataType: "json",
            success: function () {

                $.messager.alert("系统提示","审核成功").window('resize',{top: 100,left:400});
                if(thetype=='know') {
                    closeAduitwindow();
                }
                else {
                    closeAduitaddparawindow()
                }
                $('#tabtask2').datagrid('reload');
            },
            error:function () {
                $.messager.alert("系统提示","失败").window('resize',{top: 100,left:400});
                if(thetype=='know') {
                    closeAduitwindow();
                }
                else {
                    closeAduitaddparawindow()
                }

            }
        })
    }
    function dothedeleteAudit() {
        var themainid=themainid=$("#mainid2").val();
        var theid=$("#taskid_para").val();
        $.ajax({
            type: "POST",
            url: "/task//auditdp.do",
            data: {
                mainid : themainid,
                taskid:  theid
            },
            dataType: "json",
            success: function () {

                $.messager.alert("系统提示","删除审核成功");
                closeAduitaddparawindow()
                $('#tabtask2').datagrid('reload');
            },
            error:function () {
                $.messager.alert("系统提示","失败");
                closeAduitaddparawindow()
            }
        })
    }

    function dothePEAudit() {
        var themainid =$("#para_edit_id").val();
        var taskid=$("#update_task_id").val();
       // alert(themainid+"   111   "+taskid);
        $.ajax({
            type: "POST",
            url: "/task/auditpe.do",
            data: {
                mainid : themainid,
                taskid:  taskid
            },
            dataType: "json",
            success: function () {

                $.messager.alert("系统提示","编辑审核成功");
                closeAduiteditwindow();
                $('#tabtask2').datagrid('reload');
            },
            error:function () {
                $.messager.alert("系统提示","失败");
                closeAduiteditwindow();

            }
        })
    }


    function audits(target) {

        $("#tabtask2").datagrid("selectRow", getRowIndex(target));
        var row = $("#tabtask2").datagrid("getSelected");
        if(row.type=='22')
        {
            $.ajax({
                type: "POST",
                url: "/paragraph/selectbyid.do",
                data: {mainid : row.mainid},
                dataType: "json",
                success: function (paragraphs) {
                    $("#dlg_aduit_edit").dialog(
                        {
                            onOpen: function () {
                                $("#update_task_id").val(row.id);
                                $("#para_edit_id").val(paragraphs.id);
                                if(row.status==1)
                                {
                                    $("#thebefore").html(htmlDecode(row.content));
                                    $("#theafter").html(htmlDecode(paragraphs.paragraphContent));
                                    $("#audit_edit_button").hide();
                                    $("#audit_edit_close").show();
                                }
                                else
                                {
                                    $("#thebefore").html(htmlDecode(paragraphs.paragraphContent));
                                    $("#theafter").html(htmlDecode(row.content));
                                    $("#audit_edit_button").show();
                                    $("#audit_edit_close").hide();
                                }
                            }
                        }
                    );
                    $("#thebefore").html(htmlDecode(paragraphs.paragraphContent));
                    $("#theafter").html(htmlDecode(row.content));
                    $("#dlg_aduit_edit").dialog("open").dialog("setTitle", '审核').window('resize',{top: 100,left:400});
                }
            })

        }
        else if(row.type=='21')
        {
            $.ajax({
                type: "POST",
                url: "/paragraph/selectbyid.do",
                data: {mainid : row.mainid},
                dataType: "json",
                success: function (paragraphs) {
                    $("#dlg_add_para").dialog(
                        {
                            onOpen: function () {
                                $("#thetitle").html(paragraphs.paragraphTitle);
                                $("#thecontent").html(htmlDecode(paragraphs.paragraphContent));
                                $("#mainid2").val(paragraphs.id);
                                $("#taskid_para").val(row.id);
                                if(row.status==1)
                                {

                                    $("#audit_addpara_button").hide();
                                    $("#audit_addpara_close").show();
                                    $("#audit_delepara_button").hide();
                                }
                                else
                                {
                                    $("#audit_addpara_button").show();
                                    $("#audit_addpara_close").hide();
                                    $("#audit_delepara_button").hide();
                                }
                            }
                        }
                    );
                    $("#dlg_add_para").dialog("open").dialog("setTitle", '添加审核').window('resize',{top: 100,left:400});
                }
            })
        }
        else if(row.type=='23')
        {
            $.ajax({
                type: "POST",
                url: "/paragraph/selectbyid.do",
                data: {mainid : row.mainid},
                dataType: "json",
                success: function (paragraphs) {
                    $("#dlg_add_para").dialog(
                        {
                            onOpen: function () {
                                $("#thetitle").html(paragraphs.paragraphTitle);
                                $("#thecontent").html(htmlDecode(paragraphs.paragraphContent));
                                $("#mainid2").val(paragraphs.id);
                                $("#taskid_para").val(row.id);
                                if(row.status==1)
                                {

                                    $("#audit_addpara_button").hide();
                                    $("#audit_addpara_close").show();
                                    $("#audit_delepara_button").hide();
                                }
                                else
                                {   $("#audit_delepara_button").show();
                                    $("#audit_addpara_button").hide();
                                    $("#audit_addpara_close").hide();
                                }
                            }
                        }
                    );
                    $("#dlg_add_para").dialog("open").dialog("setTitle", '删除审核').window('resize',{top: 100,left:400});
                }
            })
        }
    }
    function closeAduitwindow() {
        $("#dlg_aduit_add").dialog("close");
    }
    function closeAduiteditwindow() {
        $("#dlg_aduit_edit").dialog("close");
    }
    function closeAduitaddparawindow()
    { $("#dlg_add_para").dialog("close");
    }
    function doaudit() {
        $.ajax({
            type: "post",
            url: "/task/auditaddkonw.do",
            data: {
            },
            datatype: "json",
            success: function () {
                $.messager.alert("系统提示","审核成功");
                //doSearchUser();
                //$('#editButton').linkbutton({disabled:true});

            },
            error:function () {
                $.messager.alert("系统提示","失败");
                //  doSearchUser();
                // $('#editButton').linkbutton({disabled:true});
            }

        });

    }
</script>



</body>
</html>
