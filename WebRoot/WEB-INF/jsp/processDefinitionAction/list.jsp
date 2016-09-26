<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>审批流程列表</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript">
    function showProcessImage( pdId ){
	    	//第一次URL编码
	    	 pdId = encodeURI(pdId);
	    	//第二次编码
	    	 pdId = encodeURI(pdId);
             var url = "processDefinition_downloadProcessImage.action?id=" + pdId + "&t=" + new Date();
	    	 window.showModalDialog(url, null, "dialogHeight:500px;dialogWidth:600px;resizable:yes");
        }
    </script> 
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 审批流程管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="200px">流程名称</td>
				<td width="80px">最新版本</td>
                <td width="300px">说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="processDefList">
        <s:iterator value="#processDefinitionList">
			<tr class="TableDetail1 template">
					<td>${name}&nbsp;</td>
					<td align="CENTER">${version}&nbsp;</td>
					<td>${processDefinition}&nbsp;</td>
					<td><s:a action="processDefinition_delete" onClick="return delConfirm()" >删除
					     <s:param name="key" value="%{@java.net.URLEncoder@encode(key,'utf-8')}"></s:param>
					</s:a>
						<a href="javascript: showProcessImage('${id}')">查看流程图</a>
					</td>
			</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <table border="0" cellspacing="0" cellpadding="10" align="left">
                <tr>
			        <td><div class="FuncBtn">
                            <div class=FuncBtnHead></div>
                            <div class=FuncBtnMemo><s:a action="processDefinition_addUI">部署流程定义文档</s:a></div>
                            <div class=FuncBtnTail></div>
                        </div></td>
                </tr>
			</table>
        </div>
    </div>
</div>
</body>
</html>
