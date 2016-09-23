package com.linjw.myoa.base;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.linjw.myoa.model.User;
import com.linjw.myoa.service.ApplicationService;
import com.linjw.myoa.service.ApplicationTemplateService;
import com.linjw.myoa.service.DepartmentService;
import com.linjw.myoa.service.ForumService;
import com.linjw.myoa.service.NoticeInfoService;
import com.linjw.myoa.service.PrivilegeService;
import com.linjw.myoa.service.ProcessDefinitionService;
import com.linjw.myoa.service.ReplyService;
import com.linjw.myoa.service.StationService;
import com.linjw.myoa.service.TopicService;
import com.linjw.myoa.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;





@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport {

	

	// =============== Service实例的声明 ==================
	@Resource
	protected StationService stationService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;
	@Resource
	protected ForumService forumService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;
	@Resource
	protected ProcessDefinitionService processDefinitionService;
	@Resource
	protected ApplicationTemplateService applicationTemplateService;
	@Resource
	protected ApplicationService applicationService;
	@Resource
	protected NoticeInfoService noticeInfoService;
	
	/**
	 * 获取当前登录的用户
	 */
	protected User getCurrentUser(){
		return(User)ActionContext.getContext().getSession().get("user");
	}
	
	//==========================分布用的参数============================
	protected int pageNum = 1;//当前页
	protected int pageSize = 10;//每页显示多少条记录

	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	//===========================================================================
	/**
	 * 保存上传的文件，并返回文件在服务器端的真实存在路径
	 */
	
	protected String saveUploadFile(File upload) {
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		//获取路径（服务器上传的路径）
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload_files");
		String subPath = sdf.format(new Date());
		//如果文件夹不存在，就创建
		File dir = new File(basePath + subPath);
		if(!dir.exists()){
			dir.mkdirs();//递归的创建不存在的文件夹
		}
		//拼接路径
		String path = basePath + subPath + UUID.randomUUID().toString();
		upload.renameTo(new File(path));//如果目标文件夹不存在，或是目标文件夹已存在，就不会成功，返回false，但不报错。
		return path;
		
	}
	
}
