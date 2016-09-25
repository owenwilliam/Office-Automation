package com.linjw.myoa.view.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.ModelDrivenBaseAction;
import com.linjw.myoa.model.Chart;
import com.linjw.myoa.model.Privilege;
import com.linjw.myoa.model.Station;
import com.linjw.myoa.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;



@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class StationAction extends ModelDrivenBaseAction<Station>{

  private Long[] privilegeIds;	
	/** 列表显示*/
	public String list() throws Exception{
		/*List<Station> stationList = stationService.findAll();
		ActionContext.getContext().put("stationList",stationList);*/
		// 准备分页信息
		new QueryHelper(Station.class, "s").preparePageBean(stationService, pageNum, pageSize);
		return "list";
	}
	/**删除*/
	public String delete()throws Exception{
		stationService.delete(model.getId());
		return "toList";
	}
	/**添加页面*/
	public String addUI()throws Exception{
		return "addUI";
	}
	/**添加成功后*/
	public String add() throws Exception{
		stationService.save(model);
		return "toList";
	}
	/**修改页面*/
	public String editUI()throws Exception{
		//准备回显数据
		Station stations = stationService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(stations);
		return "editUI";
	}
	/**修改*/
	public String edit()throws Exception{
		//1.从数据库中获取对象
		Station stations = stationService.getById(model.getId());
		//2.设置要修改的属性
		stations.setName(model.getName());
		stations.setDescription(model.getDescription());
		stationService.update(stations);
		return "toList";
	}
	
	
	/**权根设置页面*/
	public String setPrivilegeUI()throws Exception{
		//准备回显数据
		Station stations = stationService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(stations);
		
		if(stations.getPrivileges() !=null){
			privilegeIds = new Long[stations.getPrivileges().size()];
			int index=0;
			for(Privilege priv : stations.getPrivileges()){
				privilegeIds[index++]=priv.getId();
			}
			//准备回显数据,privilegeList
			List<Privilege> privilegeList = privilegeService.findAll();
			ActionContext.getContext().put("privilegeList", privilegeList);
		}
		return "setPrivilegeUI";
	}
	/**权根设置*/
	public String setPrivilege()throws Exception{
		//1.从数据库中获取对象
		Station stations = stationService.getById(model.getId());
		//2.设置要修改的属性
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		stations.setPrivileges(new HashSet<Privilege>(privilegeList));
		//更新数据
		stationService.update(stations);
		return "toList";
	}
	
	/**
	 * 图表---饼图
	 * @return
	 */
	//获取数据集
	 private DefaultPieDataset getDataSet2() { 
	    	
   List<Station> stations = stationService.findAll();
   DefaultPieDataset dataset = new DefaultPieDataset(); 
	    for(Station s : stations){   
	        dataset.setValue(s.getName(),s.getCount()); 
	    }
	        return dataset; 
	    }
	 
	 /**
	  * 柱图
	  * @return
	  */
	 //收集数据
	 private CategoryDataset getDataSet() { 
		 List<Station> stations = stationService.findAll();
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		 for(Station s : stations){
			 dataset.addValue(s.getCount(),s.getName(),s.getName());
		 }
		 return dataset; 
	 } 

	//报表
	 public String chart() throws IOException{ 
		 DefaultPieDataset dataset = getDataSet2(); 
		 CategoryDataset dataset_bar = getDataSet(); 
		 Chart chart = new Chart();
           chart.PieChar(dataset,"岗位统计>>饼图","pieChart.jpg");
           chart.BarChart(dataset_bar, "岗位统计>>柱图", "岗位","人数", "barChart.jpg");
       return "chart";

	    } 
	 

	//---------
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	
}
