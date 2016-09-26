<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>添加讯友</title>
      <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 讯友信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="personinfo_add" enctype="multipart/form-data">
    <s:hidden name="groupId"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 讯友信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
           
                    <tr><td>姓名</td>
                        <td><input type="text" name="name" class="InputStyle"/> *</td>
                    </tr>
					<tr><td>性别</td>
                        <td><input type="RADIO" name="sex" value="男" id="male"/><label for="male">男</label>
							<input type="RADIO" name="sex" value="女" id="female"/><label for="female">女</label>
						</td>
                    </tr>
                     <tr><td>年龄</td>
                        <td><input type="text" name="year" class="InputStyle"/> *</td>
                         <td rowspan="5" align="right">
						<img src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"/>
						</td>
                    </tr>
					<tr><td>联系电话</td>
                        <td><input type="text" name="mobile" class="InputStyle"/></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><input type="text" name="email" class="InputStyle"/></td>
                    </tr>
                    <tr><td>地址</td>
                        <td><input type="text" name="address" class="InputStyle"/></td>
                    </tr>
                     <td>头像</td>
                        <td><input type="file" name="upload" class="InputStyle" style="width: 400px;"/></td>
                    </tr>
                    <tr><td>描述</td>
                        <td><textarea name="description" class="TextareaStyle"></textarea></td>
                    </tr>
                </table>
            </div>
        </div>
    
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
</body>
</html>
