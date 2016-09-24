package com.linjw.myoa.util;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linjw.myoa.model.Privilege;
import com.linjw.myoa.model.User;

/**
 * 
 * @author 林剑文　　　2014-7-10
 *权限的初始化设置
 */
@Component
public class Installer {
	@Resource
	private SessionFactory sessionFactory;
	/**
	 * 执行安装
	 */
	@Transactional
	public void install(){
		Session session = sessionFactory.getCurrentSession();
	
	
	//===============================================================
	//保存超级管理员用户
		User user = new User();
		user.setLoginName("admin");
		user.setName("超级管理员");
		user.setPassword(DigestUtils.md5Hex("admin"));
		session.save(user);
		Privilege menu,menu1,menu2,menu3,menu4,menu5;
	//--------------------------------------------------
	//系统管理权根
		
		menu = new Privilege("系统管理",null,null);
		menu1 = new Privilege("岗位管理","/station_list",menu);
		menu2 = new Privilege("部门管理","/department_list",menu);
		menu3 = new Privilege("用户管理","/user_list",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
	
		//岗位权根
		session.save(new Privilege("岗位列表","/station_list",menu1));
		session.save(new Privilege("岗位添加","/station_add",menu1));
		session.save(new Privilege("岗位删除","/station_delete",menu1));
		session.save(new Privilege("岗位修改","/station_edit",menu1));
		
		//部门权根
		session.save(new Privilege("部门列表","/department_list",menu2));
		session.save(new Privilege("部门添加","/department_add",menu2));
		session.save(new Privilege("部门删除","/department_delete",menu2));
		session.save(new Privilege("部门修改","/department_edit",menu2));
		
		//用户管理
		
		session.save(new Privilege("用户列表","/user_list",menu3));
		session.save(new Privilege("用户添加","/user_add",menu3));
		session.save(new Privilege("用户删除","/user_delete",menu3));
		session.save(new Privilege("用户修改","/user_edit",menu3));
		session.save(new Privilege("初始化密码", "/user_initPassword", menu3));
		//-------------------------------------------------------------------------
		//网上交流
		menu = new Privilege("网上交流",null,null);
		menu1 = new Privilege("论坛管理","/forumManage_list",menu);
		menu2 = new Privilege("论坛","/forum_list",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		//---------------------------------------------------------------------------
		//审批流程
		menu = new Privilege("审批流转",null,null);
		menu1 = new Privilege("审批流程管理","/processDefinition_list",menu);
		menu2 = new Privilege("表单模板管理","/template_list",menu);
		menu3 = new Privilege("起草申请","/flow_templateList",menu);
		menu4 = new Privilege("待我审批","/flow_myTaskList",menu);
		menu5 = new Privilege("我的申请查询","/flow_myApplicationList",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		//------------------------------------------------------------------------------
		//个人设置
		menu = new Privilege("个人设置",null,null);
		menu1 = new Privilege("个人信息","/person_userInfoUI",menu);
		menu2 = new Privilege("密码修改","/person_editPasswordUI",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		//信息公告
		menu = new Privilege("信息公告",null,null);
		menu1 = new Privilege("信息","/noticeinfo_list",menu);
		menu2 = new Privilege("发布信息","/noticeinfo_addUI",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		//个人办公
		menu = new Privilege("个人办公",null,null);
		menu1 = new Privilege("个人考勤","/myOfficail_attend",menu);
		menu2 = new Privilege("日程安排","/myOfficial_dayPlan",menu);
		menu3 = new Privilege("工作计划","/myOfficial_workPlan",menu);
		menu4 = new Privilege("工作日记","/myOfficial_workWord",menu);
		menu5 = new Privilege("通讯录","/myOfficial_tell",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		//个人通讯
		menu = new Privilege("个人通讯",null,null);
		menu1 = new Privilege("通讯列表","/personinfo_list",menu);
		menu2 = new Privilege("通讯分组","/group_list",menu);
		menu3 = new Privilege("讯友统计","/personinfo_chart",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
    }
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	    Installer installer = (Installer)ac.getBean("installer");
	    installer.install();
	}
}
