package com.linjw.myoa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.BaseAction;
import com.linjw.myoa.model.NoticeInfo;
import com.linjw.myoa.model.TaskView;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class HomeAction extends BaseAction{
	 
	public String top() throws Exception{
		//滚动的信息
		List<NoticeInfo> noticeInfos = noticeInfoService.getByRoll();
		ActionContext.getContext().put("noticeInfos",noticeInfos);
		int count=0;
		//审批信息
		List<TaskView> taskViewList = applicationService.getMyTaskViewList(getCurrentUser());
		for(int i=0;i<taskViewList.size();i++){
			count++;
		}
		ActionContext.getContext().put("count", count);
		return "top";
	}
	
	public String index()throws Exception{
		return "index";
	}
	
	public String bottom()throws Exception{
		return "bottom";
	}
	
	public String left()throws Exception{
		return "left";
	}
	
	public String right()throws Exception{
		return "right";
	}

}
