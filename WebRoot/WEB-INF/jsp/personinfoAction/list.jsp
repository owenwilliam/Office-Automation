<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>分组显示</title>
	  <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>  
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	
</head>
<body>
<div id="Title_bar">
	<div id="Title_bar_Head">
		<div id="Title_Head"></div>
		<div id="Title">
			<!--页面标题-->
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 通讯分组 </div>
		<div id="Title_End"></div>
	</div>
</div>
<div id="MainArea">
	<center>
		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			
				<!--表头-->
				<tr align="center" valign="middle">
					<td colspan="3" class="ForumPageTableTitleLeft"></td>
					<td width="80" class="ForumPageTableTitle">人数</td>

				</tr>

			
				<!--版面列表-->
				<tbody class="dataContainer" datakey="forumList">
				<s:iterator value="#groupList">
				<tr height="78" align="center" class="template">
					<td width="3"></td>
					<td width="75" class="ForumPageTableDataLine">
						<img src="${pageContext.request.contextPath}/style/images/forumpage2.gif" />
					</td>
					<td class="ForumPageTableDataLine">
						<ul class="ForumPageTopicUl">
							<li class="ForumPageTopic"><s:a cssClass="ForumPageTopic" action="personinfo_show?groupId=%{id}">${name}</s:a></li>
							<li class="ForumPageTopicMemo">${description}</li>
						</ul>
						</td>
					<td class="ForumPageTableDataLine">
							<b>${count}</b>
					</td>
				</tr>
				</s:iterator>
				</tbody>
				<!-- 版面列表结束 -->
			</table>
		</div>
	</center>
</div>
</body>
</html>
