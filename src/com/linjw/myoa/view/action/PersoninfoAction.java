package com.linjw.myoa.view.action;



import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.ModelDrivenBaseAction;
import com.linjw.myoa.model.Chart;
import com.linjw.myoa.model.Group;
import com.linjw.myoa.model.Personinfo;
import com.linjw.myoa.model.User;
import com.linjw.myoa.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PersoninfoAction extends ModelDrivenBaseAction<Personinfo>{
	
	private Long groupId;
	private File upload;
	private String findname;
	
	/** 列表 ---显示版块的*/
	public String list() throws Exception {
		User person = userService.getById(getCurrentUser().getId());
		List<Group> groupList = groupService.getByUser(person);
		ActionContext.getContext().put("groupList", groupList);
		return "list";    
		}

	/**
	 * 列表---具体的组信息
	 */
	
	public String show ()throws Exception{
		Group group = groupService.getById(groupId);
		ActionContext.getContext().getValueStack().push(group);
		new QueryHelper(Personinfo.class,"p")//
		                .addCondition("p.group=?",group)//
		                .addCondition("p.person=?",getCurrentUser())//
		                .addCondition(StringUtils.isNotBlank(findname),"p.name=?",findname)
		                 .preparePageBean(personinfoService, pageNum, pageSize);
		return "show";
	}
	
	/** 删除 */
	public String delete() throws Exception { 
		personinfoService.delete(model.getId());
	
		return "toShow";  
		}

	/** 添加页面 */
	public String addUI() throws Exception { 
		return "addUI";
		}

	/** 添加 */
	public String add() throws Exception { 
		//>>创建文件来夹用来放图片
		if(upload != null){//如果upload不为空
		String path = saveUploadFile(upload);
		model.setImg_path(path); 
		}
		//维护相关数据
		Group group = groupService.getById(groupId);
		group.setCount(group.getCount()+1);
		model.setPerson(getCurrentUser());
		model.setGroup(group);
		personinfoService.save(model);
		return "toShow"; 
		}

	/** 修改页面 */
	public String editUI() throws Exception { 
		
		//回显组别
		List<Group> groupList = groupService.findAll();
		ActionContext.getContext().put("groupList", groupList);
		
		Personinfo personinfo = personinfoService.getById(model.getId());
	   ActionContext.getContext().getValueStack().push(personinfo);
		return "editUI"; 
		}

	/** 修改 */
	public String edit() throws Exception { 
	   Personinfo personinfo = personinfoService.getById(model.getId());
	   personinfo.setName(model.getName());
	   personinfo.setSex(model.getSex());
	   personinfo.setYear(model.getYear());
	   personinfo.setMobile(model.getMobile());
	   personinfo.setEmail(model.getEmail());
	   personinfo.setAddress(model.getAddress());
	   personinfo.setDescription(model.getDescription());
	   
	   
	   Group group = groupService.getById(groupId);
	   if(group.getCount()>0){
	   group.setCount(group.getCount()-1);
	   }
	   personinfo.setGroup(group);
	 //>>创建文件夹用来放图片
	   if(upload != null){//如果upload不为空
	 		String path = saveUploadFile(upload);
	 		personinfo.setImg_path(path);
	 	}
	   personinfoService.update(personinfo);
		return "toShow"; 
		}
	/**
	 * 图表---饼图
	 * @return
	 */
	//获取数据集
	 private DefaultPieDataset getDataSet2() { 
	    	
   List<Group> groups = groupService.findAll();
   DefaultPieDataset dataset = new DefaultPieDataset(); 
	    for(Group g : groups){   
	        dataset.setValue(g.getName(),g.getCount()); 
	    }
	        return dataset; 
	    } 
	//报表
	 public String chart() throws IOException{ 
		 
	        
		 DefaultPieDataset dataset = getDataSet2(); 
		 Chart chart = new Chart();
           chart.PieChar(dataset,"讯友统计","pieChart.jpg");
       return "chart";


	    } 
	//----------
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getFindname() {
		return findname;
	}

	public void setFindname(String findname) {
		this.findname = findname;
	}


}
