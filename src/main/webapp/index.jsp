<%--
  Created by IntelliJ IDEA.
  User: 李家兴
  Date: 2018/4/14
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据库课程资源</title>

    <!-- 使得网页自适应手机屏幕 -->
    <meta name="viewport" http-equiv="Content-type" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">

    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <link rel="stylesheet" type="text/css"
          href="/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript"
            src="/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>

    <link rel="stylesheet" type="text/css"
          href="/font-awesome-4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="/css/knowledgepoint.css">
    <script type="text/javascript" charset="utf-8" src="/js/user_load.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/user_search.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/knowledgepoint_manager.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/knowledgepoint_search.js"></script>

</head>
<body>
<%
    if(session.getAttribute("user")!=null&&session.getAttribute("power")!=null){
%>
<p align="center" style="padding-left: 50%"><%=session.getAttribute("user")%>登录 <a href="javascript:userLogout()"> 注销</a>
<%
    int userpower=0;
    userpower=Integer.parseInt(session.getAttribute("power").toString());
    if(userpower==9)
    {
%>
 <a href="test2.jsp">  管理界面</a></p>
<%
    }
    }else{
%>
<p align="center" style="padding-left: 55%"> <a  href="login.jsp" style="align-content: center">前往登录</a></p>
<%
    }
%>
<div id='kbknowledgepoint' class='kbknowledgepoint'>

</div>
<div id='kbparagraph' class="kbparagraph">

</div>

<div id="dlg_search" class="easyui-dialog"
     style="width: 850px;height:555px;padding: 10px 20px; position: relative; z-index:1000;"
     closed="true" buttons="#dlg-search-buttons"  data-options="iconCls:'icon-close',resizable:true,modal:true, maximizable:true, maximized:true ">
    <div class="knowledgepoint_search">

        <input id="ss" class="easyui-searchbox" style="width:400px" data-options="menu:'#box'"></input>

        <div id="box" style="width:120px">
            <div data-options="name:'knowledgepoint',iconCls:'icon-ok'">知识点</div>
            <div data-options="name:'exercise',iconCls:'icon-edit'">习题</div>
            <div data-options="name:'video',iconCls:'icon-advice'">视频</div>
            <div data-options="name:'literature',iconCls:'icon-school'">文献</div>
        </div>

    </div>

    <div id="search_result">
        <div id='kp_inner'></div>
        <!-- 在此显示查询结果-->
    </div>
    <div id="dlg-search-buttons">
        <a href="javascript:closeSearchDialog()"    class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
    </div>
</div>

<div id="dlg_user_search" class="easyui-dialog"
     style="width: 850px;height:555px;padding: 10px 20px; position: relative; z-index:1000;"
     closed="true" buttons="#dlg-search-buttons"  data-options="iconCls:'icon-close',resizable:true,modal:true, maximizable:true, maximized:true ">
    <div class="user_search">

        <input id="ssu" class="easyui-searchbox" style="width:400px" data-options="menu:'#box2'"></input>
        <div id="box2" style="width:120px">
            <div data-options="name:'user',iconCls:'icon-user'">用户</div>
        </div>

    </div>

    <div id="search_results">
        <div id='kp_inneruser'></div>
        <!-- 在此显示查询结果-->
    </div>
    <div id="dlg-usersearch-buttons">
        <a href="javascript:closeUserSearchDialog()"    class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
    </div>
</div>

<div id="dlg_add_knowledgepoint" class="easyui-dialog"
     style="width: 260px;height:130px;padding: 10px 20px; position: relative; z-index:1000;"
     closed="true" maximizable="false" closable="false" buttons="#dlg_add_knowledgepoint_buttons"  data-options="modal:true">
    <input id="input_knowledgepoint" name="konwledgepointName" class="easyui-textbox" style="width:200px" ></input>

    <div id="dlg_add_knowledgepoint_buttons">
        <a href="javascript:addKnowledgepoint()" class="easyui-linkbutton"
           iconCls="icon-ok">确定</a> <a href="javascript:closeKnowledgepointAddDialog()"
                                       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
</div>

<div id="dlg_edit_paragraph" class="easyui-dialog"
     style="width: 850px;height:555px;padding: 10px 20px; position: relative; z-index:1000;"
     closed="true" buttons="#dlg_edit_paragraph_buttons"  data-options="iconCls:'icon-close',resizable:true,modal:true, maximizable:true, maximized:true ">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td id="editor">
                </td>
            </tr>
        </table>
    </form>

    <div id="dlg_edit_paragraph_buttons">
        <a href="javascript:saveArticle()" class="easyui-linkbutton"
           iconCls="icon-ok">保存</a> <a href="javascript:closeArticleDialog()"
                                       class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
    </div>
</div>

</body>

<script>

    /*增加，编辑时使用的交互的url*/
    var url = "";
    $(window).load ( function () {

        /*查询, 可以尝试增加自动补全功能。
        TODO:点击搜索按钮后弹出搜索对话框，支持实时查询数据库自动补全，点击搜索按钮后列出查询结果。
        */
        $('#ss').searchbox({
            searcher: function (value, name) {
                doSearchKnowledge(value, name);
                /*window.location.href='index.html?qname='+value;*/
            },
            menu: '#box',
            prompt: '请输入内容',
        });
        /*查找用户
        */
        $('#ssu').searchbox({
            searcher: function (value, name) {
                doSearchUser(value,name);
                /*window.location.href='index.html?qname='+value;*/
            },
            menu: '#box',
            prompt: '请输入内容',
        });

        /*为增加知识点的文本框增加回车事件。*/
        $('#input_knowledgepoint').bind('keypress',function(event){
            if(event.keyCode == "13")
            {
                addKnowledgepoint();
            }
        });

        var qid = getQueryString("qid");
        if ((qid == null)||(qid == '')){
            qid = 154;
        }
        loadKnowledgepointParagraph(qid);
    })
</script>

</html>
