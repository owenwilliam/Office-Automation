<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
   <%@ include file="/WEB-INF/jsp/public/commons.jspf" %> 
 
<html>
<head>
    <title>分组具体显示</title>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>讯友统计图
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<div id="QueryArea">
	<div style="height: 30px">
		 
		<table border=0 cellspacing=3 cellpadding=5>
			<tr>
			  <img src="${pieChart}"/>

			</tr>
		</table>

	</div>
</div>


</body>
</html>
