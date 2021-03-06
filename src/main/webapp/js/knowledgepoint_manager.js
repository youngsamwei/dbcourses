    var url;

    /* 与index.html配合 */
    function setEditor(){

        $('.content').click(function(e){
            var $target = $(this);
            var content = $target.html();
            openArticleModifyDialog('0',content);
        })
    }

    function resetEditor(editorID) {
        return UE.getEditor(editorID, {
            initialFrameHeight: '100%',
            initialFrameWidth: '100%',
            enableAutoSave: false,
            elementPathEnabled: false,
            wordCount: false
            /*,  toolbars: [
             [
             'fontfamily', 'fontsize', 'forecolor', 'backcolor', 'bold', 'italic', 'underline', '|',
             'link', '|',
             ]
             ]  */
        });
    }

    function openArticleModifyDialog(id, contentid,powers) {
        var msg = getUserPower(powers);
        if (msg != '获得权限') {
            $.messager.alert("系统提示",msg);
            return;
        }

        $("#dlg_edit_paragraph").dialog("open").dialog("setTitle", "编辑段落");
        $('#dlg_edit_paragraph').window('center');

        var html = '<div id="myEditor" name="paragraphContent"></div>';
        $('#editor').append(html);

        var ue =  resetEditor("myEditor");

        var $target = $('#' + contentid);
        var content = $target.html();
        ue.ready(function(){
            ue.setContent(content);
            ue.focus();
        });
        url = "/task/editpara.do?id="  + id ;

    }

    function closeArticleDialog() {
        $("#dlg_edit_paragraph").dialog("close");
    }

    function openArticleAddDialog(knowledgepointId, paragraphOrder,powers) {
        var msg = getUserPower(powers);
        if (msg != '获得权限') {
            $.messager.alert("系统提示",msg);
            return;
        }

        var html = '<div id="myEditor" style="width:100%; height:100%;" name="paragraphContent"></div>';
        $('#editor').append(html);

        var ue =  resetEditor("myEditor");
        ue.ready(function(){
            ue.setContent("");
            ue.focus();
        });
        $("#dlg_edit_paragraph").dialog("open").dialog("setTitle", "添加段落");
        url = "/paragraph/add.do?knowledgepointId=" + knowledgepointId + "&paragraphOrder=" + paragraphOrder;
    }

    function saveArticle() {
        $("#fm").form("submit", {
            url: url,
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function (result) {
                $.messager.alert("系统提示","保存成功，等待审核");
                $("#dlg_edit_paragraph").dialog("close");
                //location.reload() ;
            }
        });
    }

    function deleteArticle(paragraphId,powers) {

        var msg = getUserPower(powers);
        if (msg != '获得权限') {
            $.messager.alert("系统提示",msg);
            return;
        }
        $.messager
            .confirm(
                "系统提示",
                "您确认要删除该段落吗？",
                function (r) {
                    if (r) {
                        $
                            .post(
                                "/task/paradelete.do",
                                {
                                    mainid: paragraphId,
                                    type: '23'
                                },
                                function (result) {
                                    if (result.success) {
                                        $.messager.alert(
                                            "系统提示",
                                            result.msg);

                                    } else {
                                        $.messager.alert(
                                            "系统提示",
                                            result.msg);
                                    }
                                }, "json");
                    }
                });

    }

    function openKnowledgepointAddDialog(powers){
        var msg = getUserPower(powers);
        if (msg != '获得权限') {
            alert(msg);
            return;
        }
            $("#dlg_add_knowledgepoint").dialog("open").dialog("setTitle", "请输入知识点名称");
            $('#input_knowledgepoint').focus();
    }

    function closeKnowledgepointAddDialog(){
            $("#dlg_add_knowledgepoint").dialog("close");
    }

    function addKnowledgepoint(){
        var knowledgepointName = $('#input_knowledgepoint').val();
        $.ajax({
            type: "POST",
            url: "/knowledgepoint/add.do?k" +
            "nowledgepointName=" + knowledgepointName,
            contentType: "application/json; charset=utf-8",
            data: "{}",
            dataType: "json",
            success: function (result) {
                closeKnowledgepointAddDialog();
                if("添加成功"==(result.msg))
                {
                    $.messager.alert("系统提示","添加成功");

                }
                else{
                    $.messager.alert("系统提示","知识点已存在");
                    loadKnowledgepointParagraph1(result);
                    // loadKnowledgepointParagraph(result.id);
                    // window.location.href='index.html?qname='+result.knowledgepointName;

                    // $("<div id='kbknowledgepoint' class='kbknowledgepoint'></div>").appendTo("body"); ;
                    // $("<div id='kbparagraph' class='kbparagraph'></div>").appendTo("body"); ;
                    //
                    // displayTitle(result);
                    // displayDescBlocks(result);
                }

            },
            error: function (result) {
                var response = result.responseText;
                $.messager.alert("系统提示",'error');
            }

        });
    }

/*
移动知识点段落。缺点是数据库操作数据可能过大。遍历移动
*/
    function sortUpOne(knowledgepointId, paragraphOrder){
        var correctParagraphOrder = paragraphOrder -1;
       $.ajax({
                   type:"POST",
                   async:false,
                   url:"/paragraph/sortup.do?knowledgepointId=" + knowledgepointId + "&paragraphOrder=" + correctParagraphOrder,
                   data:{},
                   contentType:"application/json; charset=utf-8",
                   dataType: "json",
                   success: function (result) {
                       //console.log("knowledgepointId=" + knowledgepointId + "paragraphOrder=" + correctParagraphOrder);
                      window.location.href='index.jsp?qid='+knowledgepointId;
                   },
                   "error": function (result) {
                       var response = result.responseText;
                       alert('errot');
                   }
               });
    }
        function sortDownOne(knowledgepointId, paragraphOrder){
            var correctParagraphOrder = paragraphOrder -1;
           $.ajax({
                       type:"POST",
                       async:false,
                       url:"/paragraph/sortdown.do?knowledgepointId=" + knowledgepointId + "&paragraphOrder=" + correctParagraphOrder,
                       data:{},
                       contentType:"application/json; charset=utf-8",
                       dataType: "json",
                       success: function (result) {
                           //console.log("knowledgepointId=" + knowledgepointId + "paragraphOrder=" + correctParagraphOrder);
                          window.location.href='index.jsp?qid='+knowledgepointId;
                       },
                       "error": function (result) {
                           var response = result.responseText;
                           alert('errot3');
                       }
                   });
        }

    function deleteKnowledgepoint(knowledgepointId,powers){
        var msg = getUserPower(powers);
        if (msg != '获得权限') {
            $.messager.alert("系统提示",msg);
            return;
        }
        $.messager
                        .confirm(
                                "系统提示",
                                "您确认要删除知识点吗？",
                                function (r) {
                                    if (r) {
                                        $
                                                .post(
                                                        "/task/knowdelete.do",
                                                        {
                                                            mainid: knowledgepointId,
                                                            type: '13'
                                                        },
                                                        function (result) {
                                                            if (result.success) {
                                                                $.messager.alert(
                                                                    "系统提示",
                                                                    result.msg);

                                                            } else {
                                                                $.messager.alert(
                                                                    "系统提示",
                                                                    result.msg);
                                                            }
                                                        },
                                                    "json");
                                    }
                                });
    }

    function loadKnowledgepointParagraph1 (qid){

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