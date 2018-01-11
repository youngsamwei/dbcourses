
    function openSearchDialog(){
        $("#dlg_search").dialog("open").dialog("setTitle", "查询");
    }

    function closeSearchDialog() {
        $("#dlg_search").dialog("close");
    }

/*将查询结果显示在div:search_result 中*/
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
                    kbhtml += "<li><a href='index.html?qid=" + element.id + "'>" + element.knowledgepointName + "</a></li>";
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