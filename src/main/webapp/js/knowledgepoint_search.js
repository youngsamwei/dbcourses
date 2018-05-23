
    function openSearchDialog(){
        $("#dlg_search").dialog("open").dialog("setTitle", "查询");
        $('#ss').next('span').find('input').focus();
        document.getElementById("upAndDownButton").style.display="none";
    }

    function closeSearchDialog() {
        $("#dlg_search").dialog("close");
    }
    //知识点搜索分页上一页
    function shangyiye(){
        var value = $('#ssvalue').val();
       var name = $('#box').val();
        var page = $('#pageaa').val()*1-1*1;
        doSearchKnowledge(value,"knowledgepoint",page);
    }
    //知识点搜索分页下一页
    function xiayiye(){
        var value = $('#ssvalue').val();
       var name = $('#box').val();
        var page = $('#pageaa').val()*1+1*1;
        doSearchKnowledge(value,"knowledgepoint",page);
    }

/*将查询结果显示在div:search_result 中
TODO: 需要支持分页，显示所有条数。
TODO：需要controller，service，dao的支持，把知识点和（第一个段落的前N个字）一起返回。
TODO: 支持输入时自动提示，每输入一个字或词，触发检索事件并显示出。
每项知识点的第一个段落的文字作为描述
*/

/*
created by weihongwei 2018/5/15
新增搜索选项“内容搜索”，solr搜索引擎支持
*/

    function doSearchKnowledge(value, name, page){
       if(name == "knowledgepoint"){
       document.getElementById("upAndDownButton").style.display="inline";
            $.ajax({
                type: "POST",
                url: "/knowledgepoint/list.do?knowledgepointName="+value+"&page="+page,
                contentType: "application/json; charset=utf-8",
                data: "{}",
                dataType: "json",
                success: function (result) {
                 var kbhtml = "<div id='kp_inner'>";
                    $.each(result, function (index, element) {
                        kbhtml += "<div><a href='javascript:loadKnowledgepointParagraph(" + element.id + ")'>"+element.knowledgepointName+"<input type='hidden' id='pageaa'value='"+element.page+"'>"+"<input type='hidden' id='ssvalue'value='"+value+"'>"+"</a>";
                        $.each(element.paragraphlist,function(i,e){
                            if(i==0){
                                 var a=htmlDecode(e.paragraphContent);
                                 var txt=cutstr(a,2000);
                                 kbhtml +=txt;
                            }
                        })
                        kbhtml += "</div>";
                    })

                    kbhtml += "</div>";
                    var  a = "<a id='btn' href='#' class='easyui-linkbutton'>"+"easyui"+"</a>";
                    //把html标签去掉
                    kbhtml += htmlDecode()
                    $("#kp_inner").remove();
                    $('#search_result').append(kbhtml);

                },
                "error": function (result) {
                    var response = result.responseText;
                    alert('已到达最后一页');
                }
            });
       }else if(name == "solr"){
       document.getElementById("upAndDownButton").style.display="none";
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
                                 var name = (( element.knowledgepointName == "")||( element.knowledgepointName == null))? '<P>&nbsp;</P>'
                                                          : htmlDecode(element.knowledgepointName);
                                 kbhtml += "<div><a href='javascript:loadKnowledgepointParagraph(" + element.id + ")'>" + name + "</a></div>";
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

      //搜索知识点时每项知识点的第一个段落的文字作为描述
    function cutstr(str,len)
        {
            //alert(str);
            var str_length = 0;
            var str_len = 0;
            str_cut = new String();
            str_len = str.length;
            for(var i = 0;i<str_len;i++)
            {
                a = str.charAt(i);
                str_length++;
                if(escape(a).length > 4)
                {
                    //中文字符的长度经编码之后大于4
                    str_length++;
                }
                str_cut = str_cut.concat(a);
                if(str_length>=len)
                {
                    str_cut = str_cut.concat("...");
                    return str_cut;
                }
            }
            //如果给定字符串小于指定长度，则返回源字符串；
            if(str_length<len){
                //alert(str.len+"23");
                return str;
            }
        }
