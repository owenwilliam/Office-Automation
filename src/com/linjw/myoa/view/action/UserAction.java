package com.linjw.myoa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.BaseAction;
import com.linjw.myoa.model.Department;
import com.linjw.myoa.model.Station;
import com.linjw.myoa.model.User;
import com.linjw.myoa.util.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class UserAction extends BaseAction<User> {
	private Long departmentId;
	private Long[] stationIds;
	private String md5Digest = DigestUtils.md5Hex("1234");//转化为32位的十六进制的数
	/**
	 * 列表
	 */
   public String list() throws Exception{
	   List<User> userList = userService.findAll();
	   ActionContext.getContext().put("userList",userList);
	   return "list";
   }
	
	/**
	 * 删除
	 */
	public String delete()throws Exception{
		userService.delete(model.getId());
		return "toList";
	}
	/**
	 * 添加页面
	 */
	public String addUI() throws Exception{
		//准备回显数据，departmentList
		List<Department> topList = departmentService.findToList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList",departmentList);
		
		//准备回显数据，stationList
		List<Station> stationList = stationService.findAll();
		ActionContext.getContext().put("stationList",stationList);
		return "addUI";
	}
	/**
	 * 添加
	 */
	public String add() throws Exception{
		//封装到对象中（当model是实体类型时，也可以使用model,但要设置未封装的属性）
		//>>设置所属部门
		model.setDepartment(departmentService.getById(departmentId));
		//>>设置关联的岗位
		List<Station> stationList = stationService.getByIds(stationIds);
		model.setStations(new HashSet<Station>(stationList));
		//>>设置默认密码为1234（使用MD5摘要）
		model.setPassword(md5Digest);
		
		//保存到数据库
		userService.save(model);
	    return "toList";
	}
	
	/**
	 * 修改页面
	 */
	public String editUI() throws Exception{
		//准备数据，departmentList
		List<Department> topList = departmentService.findToList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList",departmentList);
		
		//准备数据，stationList
		List<Station> stationList = stationService.findAll();
		ActionContext.getContext().put("stationList",stationList);
		
		
		//准备回显的数据，对象栈user
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		
		//给dedpartmentId与sationId设值,用于select标签
		if(user.getDepartment() != null){
			departmentId = user.getDepartment().getId();
		}
		if(user.getStations() != null){
			stationIds = new Long[user.getStations().size()];
			int index = 0;
			for(Station station : user.getStations()){
				stationIds[index++] = station.getId();
			}
		}
		
		return "editUI";
	}
	
	/**
	 * 修改
	 */
	
	public String edit() throws Exception {
		//1,从数据库中取出原对象
		User user = userService.getById(model.getId());
		//2，设置要修改的属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		//>>设置所属部门
		user.setDepartment(departmentService.getById(departmentId));
		//>>设置所属岗位
		List<Station> stationList = stationService.getByIds(stationIds);
		user.setStations(new HashSet<Station>(stationList));
		
		//>>更新到数据库
		userService.update(user);
		return "toList";
	}
	/**
	 * 初始化密码1234
	 */
	public String initPassword()throws Exception{
		//1,从数据库中取出原对象
		User user = userService.getById(model.getId());
		//>>设置默认密码为1234（使用MD5摘要）
		user.setPassword(md5Digest);
		//3,更新到数据库
		userService.update(user);
		return "toList";
	}
	
	
	/**
	 * 登录页面
	 * @return
	 */
	public String loginUI()throws Exception{
		return "loginUI";
	}
	/**
	 * 登录
	 * @return
	 */
	public String login()throws Exception{
		User user = userService.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		if(user == null){
			addFieldError("login","用户名或密码不正确！");
			return "loginUI";
		}else{
			//登录用户
			ActionContext.getContext().getSession().put("user",user);
			return "toIndex";
		}
	}
	/**
	 * 注销
	 * @return
	 */  
	public String logout() throws Exception { 
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
//----	
	
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getStationIds() {
		return stationIds;
	}

	public void setStationIds(Long[] stationIds) {
		this.stationIds = stationIds;
	}
	
}
