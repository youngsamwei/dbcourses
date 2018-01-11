
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
        var button0 = "<a href='javascript:openKnowledgepointAddDialog(0)' class='left_button top_button fa fa-plus-square-o' title='增加知识点'> 增加知识点</a> ";
        /*TODO:在所有段落的最后增加，需要特殊处理该段落的顺序编号*/
        var button1 = "<a href='javascript:openArticleAddDialog(" + knowledgepointId + ", 0)' class='middle_button top_button fa fa-plus' title='在最前增加一个段落'> 增加段落</a> ";
        var button3 = "<a href='javascript:openSearchDialog()' class='middle_button top_button fa fa-search' title='查询'> 查询</a> ";
        var button2 = "<a href='javascript:void(0)' class='right_button top_button fa fa-star-o' title='收藏，收藏一下'> 收藏</a>";

        var itemHTML = ["<P ><div style='background: #F0F8FF'>",
            button2, button1, button3, button0,
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
                        var button1 = "<a href='javascript:openArticleModifyDialog(\"" + element.id + "\",\"content_" + element.id + "\");' class='right_button fa fa-edit' title='编辑，贡献一下'> 编辑</a>";
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