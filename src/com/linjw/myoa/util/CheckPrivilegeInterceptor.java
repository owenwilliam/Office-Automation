package com.linjw.myoa.util;

import com.linjw.myoa.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 定义拦截器，拦截每个访问的url权限,不允许非法通过url访问
 * @author 林剑文　　2014-7-7
 *
 */
public class CheckPrivilegeInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
      //获取信息
		User user = (User) ActionContext.getContext().getSession().get("user");//在struts操作直接获取
		String namespace = invocation.getProxy().getNamespace();//获得当前的地址
		String actionName = invocation.getProxy().getActionName();
		String privUrl = namespace + actionName;//对应权限的URL
		
		//如果未登录
		if(user ==null){
			if(privUrl.startsWith("/user_login")){
				//如果是去登录，就放行
				return invocation.invoke();
			}else{
				//如果不是去登录，就转到登录页面
				return "loginUI";
			}
		}
		//如果已登录，就判断权限
		else{
			if(user.hasPrivilegeByUrl(privUrl)){
				//如果有权限，就放行
				return invocation.invoke();
			}else{
				//如果没有权限，就转到提示页面
				return "noPrivilegeError";
			}
		}
		
	}

}
