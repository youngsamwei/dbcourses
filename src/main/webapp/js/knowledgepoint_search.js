
    function openSearchDialog(){
        $("#dlg_search").dialog("open").dialog("setTitle", "查询");
        $('#ss').next('span').find('input').focus();
    }

    function closeSearchDialog() {
        $("#dlg_search").dialog("close");
    }
    //知识点搜索分页上一页
    function shangyiye(){
        var value = $('#ssvalue').val();
       var name = $('#box').val();
        var page = $('#pageaa').val()*1-1*1;
        // alert(name+";"+page);
        doSearchKnowledge(value,name,page);
    }
    //知识点搜索分页下一页
    function xiayiye(){
        var value = $('#ssvalue').val();
       var name = $('#box').val();
        var page = $('#pageaa').val()*1+1*1;
        // alert(name+";"+page);
        doSearchKnowledge(value,name,page);
    }
/*将查询结果显示在div:search_result 中
TODO: 需要支持分页，显示所有条数。
TODO：需要controller，service，dao的支持，把知识点和（第一个段落的前N个字）一起返回。
每项知识点的第一个段落的文字作为描述
*/
   //这个函数重新加入了页码
    function doSearchKnowledge(value, name,page){
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
                            kbhtml += htmlDecode(e.paragraphContent);
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
                alert('error');
            }
        });
    }