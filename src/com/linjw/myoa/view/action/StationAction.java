package com.linjw.myoa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.model.Station;
import com.linjw.myoa.service.StationService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class StationAction extends ActionSupport implements ModelDriven<Station>{

	@Resource
	private StationService stationService;
	private Station model = new Station();

	public Station getModel() {
		return model;
	}
	
	/** 列表显示*/
	public String list() throws Exception{
		List<Station> stationList = stationService.findAll();
		ActionContext.getContext().put("stationList",stationList);
		return "list";
	}
	/**删除*/
	public String delete()throws Exception{
		stationService.detele(model.getId());
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
}
