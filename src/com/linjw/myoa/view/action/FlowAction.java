package com.linjw.myoa.view.action;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.BaseAction;
import com.linjw.myoa.model.Application;
import com.linjw.myoa.model.ApplicationTemplate;
import com.linjw.myoa.model.ApproveInfo;
import com.linjw.myoa.model.TaskView;
import com.linjw.myoa.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class FlowAction extends BaseAction{
	
	private File upload;//上传的文件
	private Long applicationTemplateId;//模板的ID号
	
	private String taskId;
	private Long applicationId;
	private boolean approval;
	private String comment;
	
	private String outcome;
	private String applicationStatus;//查询用的

	
	//=================================申请有关的
	/**
	 * 起草申请（模板列表）
	 * @return
	 */
	public String templateList() throws Exception{
		List<ApplicationTemplate> applicationTemplateList = applicationTemplateService.findAll();
		ActionContext.getContext().put("applicationTemplateList", applicationTemplateList);
		return "applicationTemplateList";
	}
	/**提交申请页面*/
	public String submitUI() throws Exception{
		return "submitUI";
	}
	
	/**提交申请*/
	public String submit()throws Exception{
		//封装申请信息
		
		Application application = new Application();
		application.setApplicant(getCurrentUser());//申请人
		application.setPath(saveUploadFile(upload));//申请人上传文件并设置路径
		application.setApplicationTemplate(applicationTemplateService.getById(applicationTemplateId));
	/*	application.setApplyTime(new Date());
		application.setStatus(application.STATUS_RUNNING);
		
		name = URLDecoder.decode(name,"utf-8");//自己再进行一次URL编码
		application.setTitle(name);*/
		//调用业务方法（保存申请信息，并启动流动开始流转）
		applicationService.submit(application);
		return "toMyApplicationList";
	}

	/** 我的申请查询 */
	public String myApplicationList() throws Exception {
		/*List<Application> applicationList = applicationService.findByUser(getCurrentUser());
		ActionContext.getContext().put("applicationList", applicationList);*/
		// 准备分页信息
				new QueryHelper(Application.class, "a")
				.addCondition("a.applicant=?", getCurrentUser())// 
				.addCondition(applicationTemplateId !=null, "a.applicationTemplate.id=?",applicationTemplateId)//
				.addCondition(StringUtils.isNotBlank(applicationStatus),"a.status=?",applicationStatus)//
				.addOrderProperty("a.applyTime",false)//
				.preparePageBean(applicationService, pageNum, pageSize); 
		
		//准备回显数据
			List<ApplicationTemplate> applicationTemplateList = applicationTemplateService.findAll();
			ActionContext.getContext().put("applicationTemplateList", applicationTemplateList);
		return "myApplicationList";
	}
	
	//=============================================审批人有关的
	/**待我审批（我的任务列表）*/
	public String myTaskList() throws Exception {
		List<TaskView> taskViewList = applicationService.getMyTaskViewList(getCurrentUser());
		ActionContext.getContext().put("taskViewList", taskViewList);
		return "myTaskList";
	}

	/** 审批处理页面 */
	public String approveUI() throws Exception {
		Set<String> outcomes = applicationService.getOutcomesByTaskId(taskId);
		ActionContext.getContext().put("outcomes",outcomes);
		return "approveUI";
	}

	/** 审批处理 */
	public String approve() throws Exception {

		//封装
		ApproveInfo approveInfo = new ApproveInfo();
		approveInfo.setComment(comment);
		approveInfo.setApproval(approval);
		approveInfo.setApplication(applicationService.getById(applicationId));
		
		approveInfo.setApprover(getCurrentUser());
	//	approveInfo.setApprover(userService.getById(getCurrentUser().getId()));
		approveInfo.setApproveTime(new Date());
		//调用业务方法（保存本次审批信息，并办理完任务，并维护申请状态）
		applicationService.approve(approveInfo,taskId,outcome);
		return "toMyTaskList"; // 成功后转到待我审批页面
	}

	/** 查看流转记录 */
	public String approveHistory() throws Exception {
		Application application = applicationService.getById(applicationId);
		ActionContext.getContext().put("approveInfos",application.getApproveInfos());
		return "approveHistory";
	}
	/*
	*//**
	 * 测试用的
	 * @return
	 *//*
	public String test()throws Exception{
		List<ApproveInfo> approveInfos = approveInfoService.findAll();
		Calendar c = Calendar.getInstance();
		int month=0;
		for(int i=0;i<approveInfos.size();i++){
			c.setTime(approveInfos.get(i).getApproveTime());
			month= c.get(Calendar.MONTH)+1;
			System.out.println("================approceInfo  Time:"+month);
		}
		return"toMyTaskList";
	}*/
	//------
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Long getApplicationTemplateId() {
		return applicationTemplateId;
	}
	public void setApplicationTemplateId(Long applicationTemplateId) {
		this.applicationTemplateId = applicationTemplateId;
	}
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	
}
