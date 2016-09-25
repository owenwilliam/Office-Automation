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
import com.linjw.myoa.model.Department;
import com.linjw.myoa.model.Forum;

import com.linjw.myoa.util.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class DepartmentAction extends ModelDrivenBaseAction<Department>{

	
	private Long parentId;


	/**
	 * 列表
	 */
	public String list()throws Exception{
		List<Department> departmentList = null;
		  if(parentId == null){//顶级部门列表
			departmentList = departmentService.findToList();
		}else{
			departmentList = departmentService.findChildren(parentId);
			//返回上一级的设置
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent",parent);
		}
		ActionContext.getContext().put("departmentList",departmentList);
		return "list";
	}
	/**
	 * 删除
	 */
	public String delete()throws Exception{
		departmentService.delete(model.getId());
		return "toList";
	}
	/**
	 * 添加页面
	 */
	public String addUI()throws Exception{
		//准备回显数据，departmentList
		List<Department> topList = departmentService.findToList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList",departmentList);
		return "addUI";
	}
	/**
	 * 添加成功后
	 */
	public String add()throws Exception{
		//封装信息到对象中
		Department parent = departmentService.getById(parentId);//通过父类的Id查对象
		//保存到model中,因为数据库中没有parent这一项
		model.setParent(parent);
		//保存
		departmentService.save(model);
		return "toList";
	}
	/**
	 * 修改页面
	 */
	
	public String editUI()throws Exception{
		//准备回显数据，上级部门树状显示
		List<Department> topList = departmentService.findToList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList",departmentList);
		
		Department department = departmentService.getById(model.getId());//通过Id号获得相应的对象，
		ActionContext.getContext().getValueStack().push(department);//然后放到栈顶，拿的时候就直接根据名称来了
		//重新定义parentId的值
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}
		return "editUI";
	}
	/**
	 * 修改
	 */
	public String edit()throws Exception{
		//从数据库获得对象
		Department department = departmentService.getById(model.getId());
	   //设置要修改的数据
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));//设置所属的部门
		departmentService.update(department);
		//修改对象
		return "toList";
	}
	
	/**
	 * 图表---饼图
	 * @return
	 */
	//获取数据集
	 private DefaultPieDataset getDataSet2() { 
			int child_coiunt=0;
			DefaultPieDataset dataset = new DefaultPieDataset(); 
			if(parentId ==null){ 
				List<Department> deparments_father = departmentService.findToList();
			    for(Department d : deparments_father){  
			     List<Department> deparments_children = departmentService.findChildren(d.getId());
			     for(Department dc : deparments_children){
			    	 child_coiunt+=dc.getCount();
			     }
			     dataset.setValue(d.getName(),child_coiunt); 
			    }
			}else{
				 List<Department> deparments_children = departmentService.findChildren(parentId);
			    for(Department dc : deparments_children){  
			     dataset.setValue(dc.getName(),dc.getCount()); 
			    }
				
			}
	        return dataset; 
	   } 
	 
	 
	 /**
	  * 柱图
	  * @return
	  */
	 //收集数据
	 private  CategoryDataset getDataSet() { 
			int child_coiunt=0;
			 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
			if(parentId==null){ 
				List<Department> deparments_father = departmentService.findToList();
			    for(Department d : deparments_father){  
			     List<Department> deparments_children = departmentService.findChildren(d.getId());
			     for(Department dc : deparments_children){
			    	 child_coiunt+=dc.getCount();
			     }
			     dataset.addValue(child_coiunt,d.getName(),d.getName());
			    }
			}else{
				 List<Department> deparments_children = departmentService.findChildren(parentId); 
			    for(Department dc : deparments_children){  
			     dataset.addValue(dc.getCount(),dc.getName(),dc.getName());
			    }
				
			}
	        return dataset; 
	   } 
	 
	//报表
	 public String chart() throws IOException{ 
		 String piename;
		 String barname;
		 
		 if(parentId==null){ 
			 piename="部门统计>>饼图";
			 barname="部门统计>>柱图";
		 }else{
			 Department department = departmentService.getById(parentId);
			 piename=department.getName()+"统计>>饼图";
			 barname=department.getName()+"统计>>柱图";
		 }
		 //回显示数据
		 List<Department> departmentList = departmentService.findToList();
		 ActionContext.getContext().put("departmentList", departmentList);
		 DefaultPieDataset dataset = getDataSet2(); 
		 CategoryDataset dataset_bar = getDataSet(); 
		 Chart chart = new Chart();
           chart.PieChar(dataset,piename,"pieChart.jpg");
           chart.BarChart(dataset_bar, barname, "部门名称","人数", "barChart.jpg");
       return "chart";


	    } 
	
//------、
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


}
