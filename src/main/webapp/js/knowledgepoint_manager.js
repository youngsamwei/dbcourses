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

    function openArticleModifyDialog(id, contentid) {

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
        url = "/paragraph/edit.do?id="  + id;

    }

    function closeArticleDialog() {
        $("#dlg_edit_paragraph").dialog("close");
    }

    function openArticleAddDialog(knowledgepointId, paragraphOrder) {
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

    function deleteArticle(paragraphId) {

        $.messager
                .confirm(
                        "系统提示",
                        "您确认要删除这<font color=red>" + paragraphId
                        + "</font>吗？",
                        function (r) {
                            if (r) {
                                $
                                        .post(
                                                "/paragraph/delete.do",
                                                {
                                                    id: paragraphId
                                                },
                                                function (result) {
                                                    if (result.success) {
                                                        $.messager.alert(
                                                                "系统提示",
                                                                "段落已成功删除！");
                                                        window.location.href = window.location.href;///// 返回上一页
                                                    } else {
                                                        $.messager.alert(
                                                                "系统提示",
                                                                "段落删除失败！");
                                                    }
                                                }, "json");
                            }
                        });

    }

    function openKnowledgepointAddDialog(){
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
               closeKnowledgepointAddDialog();
               window.location.href='index.html?qname='+knowledgepointName;
            },
            "error": function (result) {
                var response = result.responseText;
                alert('errot');
            }

        });

    }
    function sortUpOne (paragraphId){
/**
       $.ajax({
                   type: "POST",
                   async: false,
                   //url: "/paragraph/sort.do",
                   //data: {"knowledgepointId": knowledgepointId, "paragraphOrder": paragraphOrder },
                   url: "/paragraph/sort.do?knowledgepointId=" + knowledgepointId + "&paragraphOrder=" + paragraphOrder,
                   data: {},
                   contentType: "application/json; charset=utf-8",
                   dataType: "json",
                   success: function (result) {
                      window.location.href='index.html';
                   },
                   "error": function (result) {
                       var response = result.responseText;
                       alert('errot3');
                   }
               });
**/
            //   url = "/paragraph/sort.do?knowledgepointId=" + knowledgepointId + "&paragraphOrder=" + paragraphOrder;
            //   window.location.href='index.html';
              $.messager
                                      .confirm(
                                              "系统提示",
                                              "您确认要上移段落<font color=red>" + paragraphId
                                              + "</font>吗？",
                                              function (r) {
                                                  if (r) {
                                                      $
                                                              .post(
                                                                      "/paragraph/sort.do",
                                                                      {
                                                                      id: paragraphId
                                                                     //      knowledgepointId: knowledgepointId,
                                                                     //      paragraphOrder: paragraphOrder
                                                                      },

                                                                      function (result) {
                                                                          if (result.success) {
                                                                              $.messager.alert(
                                                                                      "系统提示",
                                                                                      "上移成功！");
                                                                          } else {
                                                                              $.messager.alert(
                                                                                      "系统提示",
                                                                                      "上移失败！");
                                                                          }
                                                                      }, "json");
                                                  }
                                              });

    }

    function deleteKnowledgepoint(knowledgepointId){
        $.messager
                        .confirm(
                                "系统提示",
                                "您确认要删除知识点<font color=red>" + knowledgepointId
                                + "</font>吗？",
                                function (r) {
                                    if (r) {
                                        $
                                                .post(
                                                        "/knowledgepoint/delete.do",
                                                        {
                                                            id: knowledgepointId
                                                        },
                                                        function (result) {
                                                            if (result.success) {
                                                                $.messager.alert(
                                                                        "系统提示",
                                                                        "知识点已成功删除！");
                                                                window.location.href='index.html'
                                                            } else {
                                                                $.messager.alert(
                                                                        "系统提示",
                                                                        "知识点删除失败！");
                                                            }
                                                        }, "json");
                                    }
                                });
    }