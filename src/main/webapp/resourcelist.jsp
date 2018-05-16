<%--
  Created by IntelliJ IDEA.
  User: 李家兴
  Date: 2018/5/6
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户权限管理</title>
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
<%--<button onclick="insertPower()">添加测试</button>--%>
<%--<button onclick="deletePower()">刪除测试</button>--%>
<div style="margin:20px 0 10px 0;"></div>
<div class="easyui-panel"  style="width:1250px;height:700px;padding:10px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',split:true" style="width:200px;padding:10px">
            <div style="border:1px solid #ccc;width:150px;padding:10px">
            <ul id="MyTree"></ul>
           </div>
            <%--<button onclick="getChecked()">获得选中值</button>--%>
            <%--<button onclick="checkedResource()">选中</button>--%>
        </div>
        <div data-options="region:'east'" style="width:200px;padding:10px">
            <button onclick="insertPower()">添加权限</button>
            <%--<button onclick="getSelected()">获得权限值</button>--%>
            <%--<button onclick="getUnChecked()">全不选</button>--%>
            <ul id="UserTree"></ul>
        </div>

        <div data-options="region:'center'" style="padding:10px">
            <button onclick="deletePower()">批量删除</button>
            <table id="ts"></table>
        </div>
    </div>
</div>


<script type="text/javascript">
    var powertext;
    $("#MyTree").tree({
        //选中事件
        onClick:function(node){
            if(node.attributes.number.length>6) {
                powertext = node.attributes.number
                //alert(node.attributes.number);
                $('#ts').datagrid('reload', {
                    powerCode:powertext
                })
            }
          //  $('#UserTree').tree('options').url = "power/list.do"
        }
    });


    $('#MyTree').tree({
        url: 'power/list.do',
        checkbox: false,
        onlyLeafCheck:true,
        onBeforeExpand: function (node, param) {
            $(this).tree('options').url = "power/children.do";
        },
        onLoadSuccess:function(node,data) {
            var t = $(this);
            if (data) {
                $(data).each(function (index, d) {
                    if (this.state == 'closed') {
                        t.tree('expandAll');
                    }
                })
            }
        }
    });

    $('#UserTree').tree({
        url: 'group/getgroup.do',
        checkbox: true,
        onlyLeafCheck:false,
        async:false,
        lines:true,
        onLoadSuccess:function(node,data) {
            var t = $(this);
            if (data) {
                $(data).each(function (index, d) {
                    if (this.state == 'closed') {
                        t.tree('expandAll');
                    }
                })
            }

         },
        onBeforeExpand: function (node, param) {
            var t = $(this);
            if((node =t.tree('find', 0))!=null){
                t.tree('remove',node.target);
            }
            var ids = [];
            var rows = $('#ts').datagrid('getRows');
            for(var i=0; i<rows.length; i++) {
                ids.push(rows[i].userId);
            }
            $(this).tree('options').url = "user/getchildren.do?ids="+ids.join(',');
        }
    });

    function getSelected(){
        var nodes =$('#MyTree').tree('getSelected');
        alert(nodes.attributes.number);
    }
    function insertPower() {
        var node =$('#MyTree').tree('getSelected');
        var arr = [];
        var nodes = $('#UserTree').tree('getChecked', 'checked');
        for (var i = 0; i < nodes.length; i++) {
            arr.push(nodes[i].id);
        }
        $.ajax({
            type: "post",
            url: "/userpower/add.do",
            data: { powerCode:powertext,
                idlist:JSON.stringify(arr)
            },
            datatype: "json",
            success: function () {
                alert("添加成功");
                //doSearchUser();
                //$('#editButton').linkbutton({disabled:true});
                $('#ts').datagrid('reload', {
                    powerCode:powertext
                })

            },
            error:function () {
                alert("添加失败");
              //  doSearchUser();
               // $('#editButton').linkbutton({disabled:true});
            }

        });

    }
    function deletePower() {
        var ids = [];
        var rows = $('#ts').datagrid('getChecked');
        for(var i=0; i<rows.length; i++){
            ids.push(rows[i].userId);
        }
        $.ajax({
            type: "post",
            url: "/userpower/delete.do",
            data: {
                powerCode:powertext,
                idlist:JSON.stringify(ids)
            },
            datatype: "json",
            success: function () {
                alert("删除成功");
                //doSearchUser();
                //$('#editButton').linkbutton({disabled:true});
                $('#ts').datagrid('reload', {
                    powerCode:powertext
                })

            },
            error:function () {
                alert("删除失败");
                //  doSearchUser();
                // $('#editButton').linkbutton({disabled:true});
            }

        });

    }

    function getChecked() {
        var arr = [];
        var nodes = $('#MyTree').tree('getChecked', 'checked');
        for (var i = 0; i < nodes.length; i++) {
            arr.push(nodes[i].attributes.number);
        }
        alert(arr.join(','));
    }
    function getUnChecked() {
        var nodes = $('#UserTree').tree('getChecked', 'checked');
        for (var i = 0; i < nodes.length; i++) {
            $('#UserTree').tree('uncheck', nodes[i].target);
        }
    }
    function checkedResource() {
        var ids = [];
        var rows = $('#ts').datagrid('getRows');
        for(var i=0; i<rows.length; i++){
            ids.push(rows[i].userId);
            node = $('#UserTree').tree('find', rows[i].userId);
            $('#UserTree').tree('check', node.target);
        }
    }
    function getuserChecked() {
        var arr = [];
        var nodes = $('#UserTree').tree('getChecked', 'checked');
        for (var i = 0; i < nodes.length; i++) {
            arr.push(nodes[i].id);
        }
        alert(arr.join(','));
    }

    $(function () {
        $('#ts').datagrid({
            title: '用户管理',
            iconCls: 'icon-edit',
            rownumbers: true,
            pagination: true,
            width: 700,
            height: 550,
            singleSelect: false,
            idField: 'userId',
            url: '/user/powerCode.do',
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
                {field: 'remark', title: '备注', width: 250, editor: 'text'},
//                {
//                    field: 'action', title: 'Action', width: 150, align: 'center',
//                    formatter: function (value, row, index) {
//                        if (row.editing) {
//                            var s = '<a href="#" onclick="saverow(this)">Save</a> ';
//                            var c = '<a href="#" onclick="cancelrow(this)">Cancel</a>';
//                            return s + c;
//                        } else {
//                            var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
//                            var d = '<a href="#" onclick="deleterow(this)">删除</a>';
//                            return e + d;
//                        }
//                    }
//                }
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
            },
            onLoadSuccess:function(node,data) {
                $('#UserTree').tree('options').url = "group/getgroup.do";
                $('#UserTree').tree('reload');
            }
        });
    });
</script>

</body>
</html>
