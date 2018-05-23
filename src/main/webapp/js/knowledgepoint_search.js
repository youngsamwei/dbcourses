
    function openSearchDialog(){
        $("#dlg_search").dialog("open").dialog("setTitle", "查询");//eassyui,open表示打开控件的命令，setTitle表示执行命令
        $('#ss').next('span').find('input').focus();//获取焦点的方法
    }

    function closeSearchDialog() {
        $("#dlg_search").dialog("close");
    }
    //南宁添加相关查询
    function openRelateSearchDialog(){
        $("#dlg_relatesearch").dialog("open").dialog("setTitle", "相关查询");//eassyui,open表示打开控件的命令，setTitle表示执行命令
        $('#relatess').next('span').find('input').focus();//获取焦点的方法
    }

    function closeRelateSearchDialog() {
        $("#dlg_relatesearch").dialog("close");
    }

/*将查询结果显示在div:search_result 中
TODO: 需要支持分页，显示所有条数。
TODO：需要controller，service，dao的支持，把知识点和（第一个段落的前N个字）一起返回。
TODO: 支持输入时自动提示，每输入一个字或词，触发检索事件并显示出。
*/
    function doSearchKnowledge(value, name){
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
    }
       //南宁添加相关查询
    function doRelateSearchKnowledge(value, name){
        $.ajax({
            type: "POST",
            url: "/knowledgepoint/list1.do?knowledgepointName="+value,
            contentType: "application/json; charset=utf-8",
            data: "{}",
            dataType: "json",
            success: function (result) {
                var kbhtml = "<div id='relate_kp_inner'>";
                $.each(result, function (index, element) {
                    kbhtml += "<div><a href='javascript:loadRelateKnowledgepointParagraph(" + element.id + ")'>" + element.knowledgepointName +"(相关度: "+ element.xiangguandu +"% )" +"</a></div>";
                })
                kbhtml += "</div>";
                $("#relate_kp_inner").remove();
                $('#search_relateresult').append(kbhtml);

            },
            "error": function (result) {
                var response = result.responseText;
                alert('errot');
            }
        });
    }