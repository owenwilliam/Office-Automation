package com.linjw.myoa.view.action;

import java.io.IOException;
import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.ModelDrivenBaseAction;
import com.linjw.myoa.model.Chart;
import com.linjw.myoa.model.Forum;
import com.linjw.myoa.model.Station;
import com.linjw.myoa.model.Topic;
import com.linjw.myoa.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ForumAction extends ModelDrivenBaseAction<Forum>{
	
	/**
	 * 0表示查看全部主题
	 * 1表示只看精华
	 */
	private int viewType = 0;
	/**
	 * 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)<br>
	 * 1 表示只按最后更新时间排序<br>
	 * 2 表示只按主题发表时间排序<br>
	 * 3 表示只按回复数量排序
	 */
	private int orderBy = 0;
	/**
	 * true 表示升序<br>
	 * false 表示降序
	 */

	private boolean asc = false;
	/**
	 * 版块列表
	 */
	public String list()throws Exception{
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList",forumList);
		return "list";
	}
	/**
	 * 显示单个模块（主题板块）
	 */
	
	public String show()throws Exception{
		//准备数据forum
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum",forum);
		
	/*	//准备数据：topicList
		List<Topic> topicList = topicService.findByForum(forum);
		ActionContext.getContext().put("topicList",topicList);*/
		
		/*// // 准备分页信息 v1
		PageBean pageBean = topicService.getPageBeanByForum(pageNum, pageSize, forum);
		 ActionContext.getContext().getValueStack().push(pageBean);*/
		/* //准备分页信息v2
		 String hql = "from Topic t where t.forum=? order by (case t.type when 2 then 2 else 0 end) desc,t.lastUpdateTime desc";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(forum);
		PageBean pageBean = topicService.getPageBean(pageNum, pageSize, hql, parameters);
		ActionContext.getContext().getValueStack().push(pageBean);*/
		
	/*	//实现分布信息---选择条件查询v3
		String hql = "from Topic t where t.forum=? ";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(forum);
		
		if(viewType == 1){//1 表示只看精华帖
			hql += " and t.type=? ";
			parameters.add(Topic.TYPE_BEST);
		}
		
		if(orderBy == 1){//1 表示只按更新时间排序
			hql +=" order by t.lastUpdateTime "+(asc ? "asc" : "desc");
			
		}else if(orderBy == 2){//2 表示只按主题发表时间排序
			hql +=" order by t.postTime "+(asc ? "asc" : "desc");
		}else if(orderBy == 3){//3　表示只按回复数量排序
			hql +=" order by t.replyCount " + (asc ? "asc" : "desc");
		}else{//0　默认排序（所有置顶帖在前面，并按最后更新时间降序排序）
			hql +=" order by  (case t.type when 2 then 2 else 0 end) desc, t.lastUpdateTime desc";
		}
		
		
		PageBean pageBean = replyService.getPageBean(pageNum, pageSize, hql, parameters);
		 ActionContext.getContext().getValueStack().push(pageBean);*/
		//准备分页　---　最终版
		new QueryHelper(Topic.class,"t")//
			.addCondition("t.forum=?",forum)//
			.addCondition((viewType == 1),"t.type=?",Topic.TYPE_BEST)// 1 表示只看精华帖
					// 排序条件
		    .addOrderProperty((orderBy == 1),"t.lastUpdateTime", asc)// 1 表示只按最后更新时间排序
	        .addOrderProperty((orderBy == 2),"t.postTime", asc)// 2 表示只按主题发表时间排序
		    .addOrderProperty((orderBy == 3),"t.replyCount", asc)// 3 表示只按回复数量排序
			.addOrderProperty((orderBy == 0),"(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", asc)
			.addOrderProperty((orderBy == 0),"t.lastUpdateTime", false)// 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
			.preparePageBean(topicService, pageNum, pageSize);
		  
		
		
		return "show";
	}
	
	/**
	 * 图表---饼图
	 * @return
	 */
	//获取数据集
	 private DefaultPieDataset getDataSet2() { 
	    	
   List<Forum> forums = forumService.findAll();
   DefaultPieDataset dataset = new DefaultPieDataset(); 
	    for(Forum f : forums){   
	        dataset.setValue(f.getName(),f.getTopicCount()); 
	    }
	        return dataset; 
	    } 
	 /**
	  * 柱图
	  * @return
	  */
	 //收集数据
	 private CategoryDataset getDataSet() { 
		 List<Forum> forums = forumService.findAll();
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		 for(Forum f : forums){
			 dataset.addValue(f.getTopicCount(),f.getName(),f.getName());
		 }
		 return dataset; 
	 } 

	//报表
	 public String chart() throws IOException{ 
		 DefaultPieDataset dataset = getDataSet2();
		 CategoryDataset dataset_bar = getDataSet(); 
		 Chart chart = new Chart();
         chart.PieChar(dataset,"论坛统计>>饼图","pieChart.jpg");
         chart.BarChart(dataset_bar, "论坛统计>>柱图", "板块名称","主题数", "barChart.jpg");
       return "chart";


	    } 
	
	//---
	public int getViewType() {
		return viewType;
	}
	public void setViewType(int viewType) {
		this.viewType = viewType;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}

}

