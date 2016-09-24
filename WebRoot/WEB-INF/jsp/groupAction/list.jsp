<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  
<html>
<head>
    <title>通讯列表</title>
      <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>     
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 通讯列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">分组名称</td>
				<td width="500px">分组说明</td>
				<td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="departmentList">
        <s:iterator value="#groupList">
			<tr class="TableDetail1 template">
				<td><a href="_list_level2.html">${name}</a>&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td><s:a action="group_delete?id=%{id}" onClick="return window.confirm('您确定要删除吗？')" >删除</s:a>
					<s:a action="group_editUI?id=%{id}">修改</s:a>
				</td>
			</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="group_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>
</body>
</html>
