<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
	<title>发布信息</title>
      　<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	<script type="text/javascript">
	function onTopMenuLoad(){
		window.parent.TopMenu.location.reload(true);
		window.parent.right.location.reload(true);
	}
	</script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/fckeditor/fckeditor.js" charset="utf-8"></script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 发布信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--内容显示-->	
<div id="MainArea">
	<div id="PageHead"></div>

	<center>
	<s:form action="noticeinfo_list">
		<div class="ForumPageTableBorder dataContainer" datakey="replyList">
			
			<!--显示主题标题等-->
			

			<!-- ~~~~~~~~~~~~~~~ 显示主帖 ~~~~~~~~~~~~~~~ -->
			<div class="ListArea">
				<s:iterator value="recordList">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
				<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle" align="right" style="padding-right:12px;">
						<s:a action="noticeinfo_roll?id=%{id}" class="detail" onclick="onTopMenuLoad();"><img border="0" src="${pageContext.request.contextPath}/style/images/reply.gif" />滚动设置</s:a><%--
						<s:a action="noticeinfo_roll?id=%{id}" class="detail" onclick="onTopMenuLoad();"><img border="0" src="${pageContext.request.contextPath}/style/images/forum_comm.gif" />恢复</s:a>
							--%><!--操作列表-->
						
									<s:a action="noticeinfo_editUI?id=%{id}" class="detail" ><img border="0" src="${pageContext.request.contextPath}/style/images/edit.gif" />编辑</s:a>
									<s:a action="noticeinfo_delete?id=%{id}" class="detail" onClick="return confirm('确定要删除本信息吗？')"><img border="0" src="${pageContext.request.contextPath}/style/images/delete.gif" />删除</s:a>
						　　　　　　　<a href="javascript:scroll(0,0)"><img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
					
					</td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="4"></td></tr>
			</table>
				<table border="0" cellpadding="0" cellspacing="1" width="100%">
			
					<tr>
						<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
							<!--作者头像-->
							<div class="AuthorPhoto">
								<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/4105.gif" 
									onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/4105.gif';" />
							</div>
						</td>
						<td align="center">
							<ul class="TopicFunc">
							
								<!-- 文章表情与标题 -->
								<li class="TopicSubject">
									标题：${title}
								</li>
								<!-- 文章表情与标题 -->
								<li class="TopicSubject">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布者：${author.name}
								</li>
								<li class="TopicSubject">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布者时间：${postTime}
								</li>
							</ul>
						</td>
					</tr>
					<tr><!-- 文章内容 -->
						<td valign="top" align="center">
							<div class="Content">${content}</div>
						</td>
					</tr>
				
				</table>
				
				</s:iterator>
				
			<!--其他操作-->

			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
							<td></td>
							<td>
							　<s:select  name="orderBy" list="#{false:'降序',true:'升序' }" />
							<s:select  name="rollIf" list="#{0:'全部',1:'非滚动',2:'滚动' }" />
							
								<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
	
		</div>
			</div>
			<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->


			
	</s:form>
		</div>

	<!-- 分页 -->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>

			
	</center>
			
	
</div>

<div class="Description">
	说明：<br />
	1，主帖只在第一页显示。<br />
	2，只有是管理员才可以进行“移动”、“编辑”、“删除”、“精华”、“置顶”的操作。<br />
	3，删除主帖，就会删除所有的跟帖（回复）。<br />
</div>

</body>
</html>
