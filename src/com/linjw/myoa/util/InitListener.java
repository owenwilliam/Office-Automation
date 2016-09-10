package com.linjw.myoa.util;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.linjw.myoa.model.Privilege;
import com.linjw.myoa.service.PrivilegeService;
/**
 * 
 * @author 林剑文　2014-7-10
 * 这是个过滤器，在系统启动后，将需要的资源加载到容器中
 *
 */
public class InitListener implements ServletContextListener{

	
	public void contextInitialized(ServletContextEvent sce) {
		//获取容器与相关的service对象
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
	    PrivilegeService privilegeService = (PrivilegeService)ac.getBean("privilegeServiceImpl");
		//准备数据：topPrivilegeList
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		sce.getServletContext().setAttribute("topPrivilegeList",topPrivilegeList);
		System.out.println("-------------------->已准备数据topPrivilegeList");

		//准备数据：allPrivilegeUrls
	Collection<String> allPrivilegeUrls = privilegeService.getAllPrivilegeurls();
	sce.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
	System.out.println("-------------------->已准备数据allPrivilegeUrls");

	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
