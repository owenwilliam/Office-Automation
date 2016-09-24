<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 
<html>
<head>
    <title>分组具体显示</title>
  	  <%@ include file="/WEB-INF/jsp/public/commons.jspf" %> 
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> ${name }组
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<div id="QueryArea">
	<div style="height: 30px">
		 <s:form action="personinfo_show?groupId=%{id}">
		<table border=0 cellspacing=3 cellpadding=5>
			<tr>
				<td>按姓名查询：</td>
				<td><s:textfield  name="findname" cssClass="InputStyle {required:true}"/>
				</td>
				
				<td><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></td>
			</tr>
		</table>
</s:form>
	</div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
				<td width="250px">姓名</td>
				<td width="115px">性别</td>
				<td width="115px">年龄</td>
				<td width="115px">手机</td>
				<td width="115px">email</td>
				<td width="115px">住址</td>
				<td>相关操作</td>
			</tr>
		</thead>	
	
		<!--显示数据列表：正在审批或审批完成的表单显示示例-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
			<!-- 正在审批或审批完成的表单显示示例 -->
			<s:iterator value="recordList">
			<tr class="TableDetail1 template">
			
				<td><s:a action="personinfo_editUI?id=%{id}">${name}&nbsp;</s:a></td>
				<td>${sex}&nbsp;</td>
				<td>${year}&nbsp;</td>
				<td>${mobile}&nbsp;</td>
				<td>${email}&nbsp;</td>
				<td>${address}&nbsp;</td>
				<td><s:a action="personinfo_delete?id=%{id}&groupId=%{group.id}">删除</s:a>
					<s:a action="personinfo_editUI?id=%{id}">修改</s:a>
				&nbsp;
				</td>
		
			</tr>
				</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
		<div id="TableTail_inside">
			<s:a action="personinfo_addUI?groupId=%{id}"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
       　　　　　 <s:a action="personinfo_list"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></s:a>
        </div>
    </div>
</div>

<!--分页信息-->
  	  <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %> 


</body>
</html>
