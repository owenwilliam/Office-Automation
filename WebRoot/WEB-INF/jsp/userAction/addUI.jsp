<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>用户信息--添加</title>
      　<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>

</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="user_add" enctype="multipart/form-data">
    <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 用户信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
              
                    <tr>
                    
                    <td width="100">所属部门</td>
                        <td>
                          <s:select name="departmentId" cssClass="SelectStyle"
                        list="#departmentList" listKey="id" listValue="name"
                        headerKey="" headerValue="==请选择部门==" 
                        />
                        </td>
                        <td><font color="red"><s:fielderror/></font></td>
                    </tr>
                    <tr><td>登录名</td>
                    
                        <td><s:textfield name="loginName" cssClass="InputStyle {required:true}" /> *
							（登录名要唯一）
						</td>
						
                     <td rowspan="5" align="right">
						<img src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"/>
						</td>
						
                    </tr>
                    <tr><td>姓名</td>
                        <td><s:textfield name="name" cssClass="InputStyle {required:true}" /> *</td>
                    
                    </tr>
					<tr><td>性别</td>
                        <td><s:radio name="gender" list="{'男','女'}" cssClass="{required:true}"></s:radio>
						</td>
                    </tr>
					<tr><td>联系电话</td>
                        <td><s:textfield name="phoneNume" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td>
                        <s:textfield name="email" cssClass="InputStyple"/>
                        </td>
                    </tr>
                     <tr>
                     <td>头像</td>
                        <td><input type="file" name="upload" class="InputStyle" style="width: 400px;"/></td>
                    </tr>
                    <tr><td>备注</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    
                    </tr>
                    
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位设置 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">岗位</td>
                        <td>	<s:select name="stationIds" cssClass="SelectStyle {required:true}"
                        		multiple="true" size="10" 
                        		list="#stationList" listKey="id" listValue="name"
                        	/>
                            按住Ctrl键可以多选或取消选择
                        </td>
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
