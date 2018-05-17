<%--
  Created by IntelliJ IDEA.
  User: 李家兴
  Date: 2018/5/15
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Load Panel Content - jQuery EasyUI Demo</title>
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
<body background="/images/thebg.jpg">
<h1>管理界面</h1>
<br>
<br>
<%--<div style="margin:20px 0;">--%>
    <%--<span>Position:</span>--%>
    <%--<select onchange="$('#tt').tabs({tabPosition:this.value})">--%>
        <%--<option value="top">Top</option>--%>
        <%--<option value="bottom">Bottom</option>--%>
        <%--<option value="left">Left</option>--%>
        <%--<option value="right">Right</option>--%>
    <%--</select>--%>
<%--</div>--%>
<div id="tts" class="easyui-tabs" style="width:1300px;height:750px;margin:0 auto;"  tabPosition=left >
    <div title="用户管理" style="padding:10px" >
        <div id="pu" class="easyui-panel"  style="width:auto;height:800px;padding:10px;">
        </div>
    </div>
    <div title="资源权限管理" style="padding:10px">
        <div id="pr" class="easyui-panel"  style="width:auto;height:800px;padding:10px;"></div>
    </div>
    <div title="任务审核"  style="padding:10px" >
        <div id="pt" class="easyui-panel"  style="width:auto;height:800px;padding:10px;"></div>
    </div>
</div>
<script type="text/javascript">
    $('#pr').panel({
        href:'resourcelist.jsp',
        onLoad:function(){
           // alert('loaded successfully');
        }
    });
    $('#pu').panel({
        href:'alluserpage.jsp',
        onLoad:function(){
            //alert('loaded successfully');
        }
    });
    $('#pt').panel({
        href:'taskaudit.jsp',
        onLoad:function(){
            //alert('loaded successfully');
        }
    });
//    function panelReload(page) {
//        $('#p').panel('refresh', page);
//    }
</script>
</body>
</html>