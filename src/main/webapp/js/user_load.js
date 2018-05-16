function loadKnowledgepointParagraph (qid){

    $("#kbknowledgepoint").remove();
    $("#kbparagraph").remove();

    $("<div id='kbknowledgepoint' class='kbknowledgepoint'></div>").appendTo("body"); ;
    $("<div id='kbparagraph' class='kbparagraph'></div>").appendTo("body"); ;

    qstr = "id=" + qid;

    $.ajax({
        type: "POST",
        url: "/knowledgepoint/list.do?" + qstr,
        contentType: "application/json; charset=utf-8",
        data: "{}",
        dataType: "json",
        success: function (result) {
            if (result.length <=0)
            {
                $.messager.show({
                    title:'错误',
                    msg:'没有查到知识点',
                    showType:'fade',
                    style:{
                        right:'',
                        bottom:''
                    }
                });
            }else{
                displayTitle(result);
                displayDescBlocks(result);
                /*如果是查询跳转过来的，则需要关闭查询窗口。*/
                closeSearchDialog();
            }
        },
        "error": function (result) {
            var response = result.responseText;
            $.messager.show({
                title:'错误',
                msg:'没有查到知识点',
                showType:'fade',
                style:{
                    right:'',
                    bottom:''
                }
            });
        }
    });
}

function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]); return null;
    }

    /*获取并显示知识点的名字*/
    function displayTitle(result ){
        var knowledgepointId = result[0].id;
        var knowledgepointName = result[0].knowledgepointName;
        var button0 = "<a href='javascript:openKnowledgepointAddDialog(2)' class='left_button top_button fa fa-plus-square-o' title='增加知识点'> 增加知识点</a> ";
        /*TODO:在所有段落的最后增加，需要特殊处理该段落的顺序编号*/
        var button1 = "<a href='javascript:openArticleAddDialog(" + knowledgepointId + ", 1)' class='middle_button top_button fa fa-plus' title='在最前增加一个段落'> 增加段落</a> ";
        var button3 = "<a href='javascript:openSearchDialog()' class='middle_button top_button fa fa-search' title='查询'> 查询</a> ";
        var button2 = "<a href='javascript:void(0)' class='middle_button top_button fa fa-star-o' title='收藏，收藏一下'> 收藏</a>";
        var button4 = "<a href='javascript:openUserSearchDialog()' class='right_button top_button fa fa-search' title='用户管理'> 用户</a>";
        var itemHTML = ["<P ><div style='background: #F0F8FF'>",
            button4, button2, button1, button3, button0,
            "<div  id='content_" + knowledgepointId + "'>",
           '<h1>',  knowledgepointName,'</h1>',
            "</div>",
            "</div></P>"].join('\n');
        //console.log(itemHTML);
        $(".kbknowledgepoint").append(itemHTML);

    }
    /*显示文本块，并在文本块的右上角显示删除，编辑，纠错按钮*/
    function displayDescBlocks(result) {

            var knowledgepointId = result[0].id;
            $.ajax({
                type: "POST",
                url: "/paragraph/list.do?knowledgepointId=" + knowledgepointId,
                contentType: "application/json; charset=utf-8",
                data: "{}",
                dataType: "json",
                success: function (paragraphs) {
                    $.each(paragraphs, function (index, element) {
                        var content  = (( element.paragraphContent == "")||( element.paragraphContent == null))? '<P>&nbsp;</P>'
                          : htmlDecode(element.paragraphContent);
                        var paragraphOrder = element.paragraphOrder + 1;
                        var button1 = "<a href='javascript:openArticleModifyDialog(\"" + element.id + "\",\"content_" + element.id + "\",1);' class='right_button fa fa-edit' title='编辑，贡献一下'> 编辑</a>";
                        var button2 = "<a href='javascript:void(0)' class='middle_button fa fa-trash-o' title='删除此段落'> 删除</a> ";
                        var button3 = "<a href='javascript:void(0)' class='left_button fa fa-bug' title='纠错，较真一下'> 纠错</a>";
                        var button4 = "<a href='javascript:openArticleAddDialog(" + knowledgepointId +", " + paragraphOrder + ")' class='middle_button fa fa-plus' title='在此段落后增加'> 增加</a> ";
                        var itemHTML = ["<P ><div style='background: #F0F8FF'>",
                            button1, button4, button2, button3,
                            "<div class='content' id='content_" + element.id + "'>",
                            content,
                            "</div>",
                            "</div></P>"].join('\n');
                        //console.log(itemHTML);
                        $(".kbparagraph").append(itemHTML);
                    })
                },
                "error": function (result) {
                    var response = result.responseText;
                    alert('errot');
                }

            });

    }


//Html编码获取Html转义实体
function htmlEncode(value){
 return $('<div/>').text(value).html();
}
//Html解码获取Html实体
function htmlDecode(value){
 return $('<div/>').html(value).text();
}

//单个删除
function getSelected(){
    var row = $('#tt').datagrid('getSelected');
    if (row){
        alert('Item ID:'+row.nickName+"\nPrice:"+row.userName);
    }
}

//编辑用户
function getEditrow(){
    var userId= $('#input_userId').val();
    var userName= $('#input_userName').val();
    var nickName =$('#input_nickName').val();
    var remark =$('#input_remark').val();
    var group= $('#input_userGroup').combobox('getText');
    var power =$('#input_power').combobox('getValues');

    var user ={
        userId:userId,
        userName:userName,
        userGroup:group,
        nickName:nickName,
        power:power,
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
            $('#editButton').linkbutton({disabled:true});

        },
        error:function () {
            alert("编辑失败");
            doSearchUser();
            $('#editButton').linkbutton({disabled:true});
        }

    });
}


function getSelections(){
    var ids = [];
    var rows = $('#tt').datagrid('getChecked');
    for(var i=0; i<rows.length; i++){
        ids.push(rows[i].userId);
    }
    alert(ids.join('\n'));
}

function getDelete(){

    var ids = [];
    var rows = $('#tt').datagrid('getChecked');
    for(var i=0; i<rows.length; i++){
        ids.push(rows[i].userId);
    }

    $.ajax({
        type: "post",
        url: "/user/delete.do",
        data: { idlist: JSON.stringify(ids) },//非常重要的一步
        datatype: "json",
        success: function () {

                alert("删除成功");
                location.reload()

        },
        error:function () {
              alert("删除失败");
              location.reload()
        }

    });

}

function formatPrice() {
    return '<a href="#" onclick="geteditrow(getRowIndex(target))">编辑</a> <a style="color:red;" href="/user/delete.do">刪除</a>';
}
 function notSelect (row) {
    $(this).datagrid('unselectRow', row);
}

function closeUserDialog() {
    $("#dlg_edit_user").dialog("close");
}
function buttonAble() {
    $('#editButton').linkbutton({disabled:false});
}
function userLogout() {
    $.ajax({
        type: "post",
        url: "/user/logout.do",
        data: {  },//非常重要的一步
        datatype: "json",
        success: function () {

            alert("注销成功");
            location.reload()

        },
        error:function () {
            alert("删除失败");
            location.reload()
        }

    });
}

function getUserPower(powers){
    var msg='请求失败';
    $.ajax({
        type: "post",
        url: "/user/getpower.do",
        data: { powers: powers },
        dataType: "json",
        async:false,
        success: function (data) {
            msg=data.msg;
        },
        error: function (data) {
            msg = data.msg;
        }
    });
    return msg;
}
var row = $('#tt').datagrid('getChecked');
if (row) {
    alert('Item ID:' + row.userId
    );
}
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
