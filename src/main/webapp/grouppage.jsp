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
    <title>分组</title>
    <link rel="stylesheet" type="text/css" href="css/wu.css" />
    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
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

    <link rel="stylesheet" type="text/css"
          href="/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/knowledgepoint.css">
    <script type="text/javascript" charset="utf-8" src="/js/user_load.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/user_search.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/knowledgepoint_manager.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/knowledgepoint_search.js"></script>

</head>
<body>
<div class="wu-toolbar-button">
    <span>账号:</span>
    <input id="group_Name" class="easyui-textbox">
    分组: <input id="group_userGroup" name="userGroup" class="easyui-combobox" style="width:200px" data-options="
                              url: '/group/list.do',
                              method: 'get',
                              valueField: 'id',
                              textField: 'name',
                              multiple:false,
                              panelHeight:'auto'"></input>

    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchUser()" plain="true">查找</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openGroupadd()" plain="true">添加分组</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="" plain="true">批量删除</a>
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
<div>
<table id="tgroup" ></table>
    <script >
        $(function () {
            $('#tgroup').datagrid({
                title: '分组管理',
                iconCls: 'icon-edit',
                rownumbers: true,
                pagination: true,
                width: 1000,
                height: 500,
                singleSelect: false,
                idField: 'groupId',
                url: '/group/list.do',
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: 'groupId', hidden: true},
                    {field: 'name', title: '组名', width: 150},
                    {
                        field: 'remark',
                        title: '备注',
                        width: 250
                    },
                    {
                        field: 'action', title: '操作', width: 150, align: 'center',
                        formatter: function () {
                            var e = '<a href="#" onclick="">编辑</a> ';
                            return e ;
                        }
                    }

                ]]
            });
        })

    </script>
</div>
<script type="text/javascript" >
    function closeGroupadd() {
        $("#dlg_add_group").dialog("close");
    }

    function openGroupadd() {
        $("#dlg_add_group").dialog("open").dialog("setTitle", '输入组名').window('resize',{top: 200,left:400});
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
</script>
</body>

</html>
