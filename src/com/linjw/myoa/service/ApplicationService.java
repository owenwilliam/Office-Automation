package com.linjw.myoa.service;

import java.util.List;
import java.util.Set;

import com.linjw.myoa.base.DaoSupport;
import com.linjw.myoa.model.Application;
import com.linjw.myoa.model.ApproveInfo;
import com.linjw.myoa.model.TaskView;
import com.linjw.myoa.model.User;

public interface ApplicationService extends DaoSupport<Application>{

	
	/**
	 * 提交申请：
	 * 
	 * 1，保存申请信息
	 * 
	 * 2，启动流程开始流转
	 * 
	 * @param application
	 */
	public void submit(Application application);
   @Deprecated
	public List<Application> findByUser(User currentUser);
	/**
	 * 查询我的任务信息列表
	 * 
	 * @param currentUser
	 * @return
	 */
	public List<TaskView> getMyTaskViewList(User currentUser);
	/**
	 * 执行审批处理
	 * 1.保存本次审批信息
	 * 2.办理完生任务
	 * 3.并维护申请的状态
	 * @param approveInfo
	 * @param taskId
	 * @param outcome 使用指定的路线离开本活动，如果为null，表示使用唯一的那个路线
	 */
	public void approve(ApproveInfo approveInfo, String taskId, String outcome);
	/**
	 * 获取指定任务活动中所有流出的连线名称
	 * @param taskId
	 * @return
	 */
	public Set<String> getOutcomesByTaskId(String taskId);

}
