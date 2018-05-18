
    function openSearchDialog(){
        $("#dlg_search").dialog("open").dialog("setTitle", "查询");
        $('#ss').next('span').find('input').focus();
    }

    function closeSearchDialog() {
        $("#dlg_search").dialog("close");
    }


/*将查询结果显示在div:search_result 中
TODO: 需要支持分页，显示所有条数。
TODO：需要controller，service，dao的支持，把知识点和（第一个段落的前N个字）一起返回。
TODO: 支持输入时自动提示，每输入一个字或词，触发检索事件并显示出。
*/

/*
created by weihongwei 2018/5/15
新增搜索选项“内容搜索”，solr搜索引擎支持
*/

    function doSearchKnowledge(value, name){
       if(name == "knowledgepoint"){
             $.ajax({
                        type: "POST",
                        url: "/knowledgepoint/list.do?knowledgepointName="+value,
                        contentType: "application/json; charset=utf-8",
                        data: "{}",
                        dataType: "json",
                        success: function (result) {
                            var kbhtml = "<div id='kp_inner'>";
                            $.each(result, function (index, element) {
                                kbhtml += "<div><a href='javascript:loadKnowledgepointParagraph(" + element.id + ")'>" + element.knowledgepointName + "</a></div>";
                            })
                            kbhtml += "</div>";
                            $("#kp_inner").remove();
                            $('#search_result').append(kbhtml);
                        },
                        "error": function (result) {
                            var response = result.responseText;
                            alert('errot');
                        }
                    });
       }else if(name == "solr"){
             $.ajax({
                        type: "POST",
                        url: "/knowledgepoint/solr.do?knowledgepointName="+value,
                        contentType: "application/json; charset=utf-8",
                        data: "{}",
                        dataType: "json",
                        success: function (result) {
                            var kbhtml = "<div id='kp_inner'>";
                            $.each(result ,function (index, element){
                                 var content  = (( element.paragraphContent == "")||( element.paragraphContent == null))? '<P>&nbsp;</P>'
                                                          : htmlDecode(element.paragraphContent);
                                 kbhtml += "<div><a href='javascript:loadKnowledgepointParagraph(" + element.id + ")'>" + element.knowledgepointName + "</a></div>";
                                 kbhtml += "<div style='background: #F0F8FF'>" + content + "</div>";
                            })
                            kbhtml += "</div>";
                            $("#kp_inner").remove();
                            $('#search_result').append(kbhtml);
                        },
                        "error": function (result) {
                            var response = result.responseText;
                            alert('errot');
                        }
                    });
       }else if(name == "video"){

       }else if(name == "literature"){

       }else if(name == "exercise"){

       }

    }