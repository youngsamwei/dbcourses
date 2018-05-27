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
            alert(msg);
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
            alert(msg);
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

                $("#dlg_edit_paragraph").dialog("close");
                location.reload() ;
            }
        });
    }

    function deleteArticle(paragraphId,powers) {

        var msg = getUserPower(powers);
        if (msg != '获得权限') {
            $.messager.alert(msg);
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
                                                                "数据已成功删除！");

                                                    } else {
                                                        $.messager.alert(
                                                                "系统提示",
                                                                "数据删除失败！");
                                                    }
                                                }, "json");
                            }
                        });

    }

    //知识点增加
    function openKnowledgepointAddDialog(powers) {
       // alert(powers);
        var msg = getUserPower(powers);
        if (msg != '获得权限') {
            $.messager.alert("系统提示",msg);
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
            url: "/knowledgepoint/add.do?knowledgepointName=" + knowledgepointName,
            contentType: "application/json; charset=utf-8",
            data: "{}",
            dataType: "json",
            success: function (result) {
                if (result.msg == "添加成功") {
                    $.messager.alert("系统提示","添加成功,!等待审核");
                    closeKnowledgepointAddDialog();
                }
                else{
                    $.messager.alert("系统提示","添加失败,或已存在相同知识点");
                }
            }

        });
    }


