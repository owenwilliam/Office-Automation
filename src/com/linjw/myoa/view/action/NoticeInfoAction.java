package com.linjw.myoa.view.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.ModelDrivenBaseAction;
import com.linjw.myoa.model.NoticeInfo;
import com.linjw.myoa.model.User;
import com.linjw.myoa.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class NoticeInfoAction extends ModelDrivenBaseAction<NoticeInfo>{
	private boolean orderBy;
	private int rollIf;
	
	/** 列表 */
	public String list() throws Exception {
	  /*  List<NoticeInfo> noticeIfnoList = noticeInfoService.findAll();
	    ActionContext.getContext().put("noticeIfnoList", noticeIfnoList);*/
		new QueryHelper(NoticeInfo.class,"n")//
		.addCondition(rollIf==1,"n.roll=?",false)
		.addCondition(rollIf==2,"n.roll=?",true)
		.addOrderProperty("n.postTime", orderBy)  
		.preparePageBean(noticeInfoService, pageNum, pageSize);
		return "list";    
		}

	/** 删除 */
	public String delete() throws Exception { 
		noticeInfoService.delete(model.getId());
		return "toList"; 
		}

	/** 添加页面 */
	public String addUI() throws Exception { 
		return "addUI";
		}

	/** 添加 */
	public String add() throws Exception { 
		User author = userService.getById(getCurrentUser().getId());
		model.setAuthor(author);
		model.setPostTime(new Date());
		model.setRoll(false);
	   noticeInfoService.save(model);
		return "toList"; 
		}

	/** 修改页面 */
	public String editUI() throws Exception { 
		NoticeInfo noticeInfo = noticeInfoService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(noticeInfo);
		return "editUI"; 
		}

	/** 修改 */
	public String edit() throws Exception { 
		NoticeInfo noticeInfo = noticeInfoService.getById(model.getId());
		noticeInfo.setTitle(model.getTitle());
		noticeInfo.setContent(model.getContent());
		noticeInfoService.update(noticeInfo);
		return "toList"; 
		}
	/**
	 * 设为滚动的
	 * @return
	 */
	public String roll()throws Exception{
		NoticeInfo noticeInfo = noticeInfoService.getById(model.getId());
		if(noticeInfo.isRoll()==false){
		noticeInfo.setRoll(true);
		}else{  
			noticeInfo.setRoll(false); 
			}
		noticeInfoService.update(noticeInfo); 
		return"toList";
	}
	
	//-------



	public boolean isOrderBy() {
		return orderBy;
	}

	public void setOrderBy(boolean orderBy) {
		this.orderBy = orderBy;
	}

	public int getRollIf() {
		return rollIf;
	}

	public void setRollIf(int rollIf) {
		this.rollIf = rollIf;
	}

	

}
