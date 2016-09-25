<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
   <%@ include file="/WEB-INF/jsp/public/commons.jspf" %> 
 
<html>
<head>
    <title>部门统计</title>
    <script type="text/javascript">
		

		$(function(){
		
				$("select[name=parentId_bar]").hide();	
			
		});
	</script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>部门统计
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<div id="QueryArea">
	<div style="height: 30px">
<s:form action="department_chart">
		<table border=0 cellspacing=3 cellpadding=5>
		<tr>
				 <td>

				<s:select list="#departmentList" name="parentId" listKey="id" listValue="name"></s:select>
				<a href=""><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a>
            <s:a action="department_chart"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></s:a>
				</td>


            <td>
				<s:select list="#departmentList" name="parentId_bar" listKey="id" listValue="name"></s:select>
				</td>
			</tr>
			<tr>
			 <td> <img src="${pieChart}"/></td>
			  <td> <img src="${barChart}"/></td>
			</tr>
			<tr>
			</tr>
		</table>
</s:form>
</div>
</div>


</body>
</html>
