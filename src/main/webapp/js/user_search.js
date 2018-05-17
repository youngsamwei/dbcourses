
    function openUserSearchDialog(){
        $("#dlg_user_search").dialog("open").dialog("setTitle", "查询");
        $('#ssu').next('span').find('input').focus();
    }

    function closeUserEditDialog() {
        $("#dlg_edit_userinfo").dialog("close");
    }

/*将查询结果显示在div:search_result 中
TODO: 需要支持分页，显示所有条数。
TODO：需要controller，service，dao的支持，把知识点和（第一个段落的前N个字）一起返回。
TODO: 支持输入时自动提示，每输入一个字或词，触发检索事件并显示出。
*/
    function  doSearchUser(){
        var userName =$("#userName").val();
        var nickName =$("#nickName").val();
        var userGroup= $('#userGroup').combobox('getValue');
        if(userGroup=='')
        {
            userGroup='0';
        }
        $('#tt').datagrid('reload',{
            userName:userName,
            nickName:nickName,
            userGroup:userGroup
        })
    }

    function loadUser (qid){

        $("#kbknowledgepoint").remove();
        $("#kbparagraph").remove();

        $("<div id='kbknowledgepoint' class='kbknowledgepoint'></div>").appendTo("body"); ;
        $("<div id='kbparagraph' class='kbparagraph'></div>").appendTo("body"); ;

        qstr = "id=" + qid;

        $.ajax({
            type: "POST",
            url: "/user/list.do?" + qstr,
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