package com.linjw.myoa.view.action;

import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.ModelDrivenBaseAction;
import com.linjw.myoa.model.Department;
import com.linjw.myoa.model.NoticeInfo;
import com.linjw.myoa.model.Station;
import com.linjw.myoa.model.User;
import com.linjw.myoa.util.DepartmentUtils;
import com.linjw.myoa.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class UserAction extends ModelDrivenBaseAction<User> {
	private Long departmentId;
	private Long departmentId_c;//备份部门的id
	
	private Long[] stationIds;
	private int index_c;//备份岗位的索引
	private Long[]stationIds_c ;//备份岗位
	private boolean flag; //岗位是否变化了
	
	
	private String md5Digest = DigestUtils.md5Hex("1234");//转化为32位的十六进制的数
	
	
	private String oldPassword;
	private String newPassword;
	private String newPassword2;
	
	//查询变量
	private Long stationId;
	private String userName;
	
	private File upload;//头像
	private InputStream inputStream;//下载用的

	/**
	 * 列表
	 */
   public String list() throws Exception{
	  /* List<User> userList = userService.findAll();
	   ActionContext.getContext().put("userList",userList);*/
	   
	   //回显示数据
	   
	   //>>部门
	   List<Department> departments = departmentService.findAll();
	   ActionContext.getContext().put("departments", departments);
	   //>>岗位
	   List<Station> stations =  stationService.findAll();
	   ActionContext.getContext().put("stations",stations);
	   //查询对象
	   Department department = departmentService.getById(departmentId);
	 /*  Station station = stationService.getById(stationId);*/
	   List<Station> stationList = stationService.getByIds(stationIds);
		// 准备分页信息
		new QueryHelper(User.class, "u")
		     .addCondition(departmentId !=null,"u.department=?",department)
		     .addCondition(stationIds !=null,"u.stations in ",stationList)
		     .addCondition(StringUtils.isNotBlank(userName),"u.name=?", userName)
		     .preparePageBean(userService, pageNum, pageSize);
	   return "list";
   }
	
	/**
	 * 删除
	 */
	public String delete()throws Exception{
		//准备回显的数据，对象栈user
		User user = userService.getById(model.getId());
		//>>岗位count减一
		if(user.getStations() != null){
			stationIds = new Long[user.getStations().size()];
			int index = 0;
			for(Station station : user.getStations()){
				stationIds[index++] = station.getId();
			}
		
		
		List<Station> stationList = stationService.getByIds(stationIds);
		for(Station s:stationList){
			if(s.getCount()>0){
			s.setCount(s.getCount()-1);
			stationService.update(s);
			}
		  }
		}
		//部门count减一
		if(user.getDepartment() != null){
			departmentId = user.getDepartment().getId();
			Department department = departmentService.getById(departmentId);
			if(department.getCount()>0){
			department.setCount(department.getCount()-1);
			departmentService.update(department);
			}
		}
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
	 * 添加页面2----这个方法不好　　有待解决
	 */
	public String addUI2() throws Exception{
		//TODO 准备回显数据，departmentList
		List<Department> topList = departmentService.findToList();
		List<Department> departmentList = DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList",departmentList);
		
		//准备回显数据，stationList
		List<Station> stationList = stationService.findAll();
		ActionContext.getContext().put("stationList",stationList);
		addFieldError("aad","登录名已存在！");
		return "addUI";
	}
	/**
	 * 添加
	 */
	public String add() throws Exception{
		User user = userService.findByUserLoginName(model.getLoginName());
		if(user != null){//如果登录名已存在了
			
			 return "toaddUI"; 
			
		}else{
			if(departmentId !=null){
					Department department = departmentService.getById(departmentId); 
				//封装到对象中（当model是实体类型时，也可以使用model,但要设置未封装的属性）
				//>>设置所属部门
				model.setDepartment(department);
				//>>设置所属部门的count的值
				department.setCount(department.getCount()+1);
				departmentService.update(department);
			}
		//>>设置关联的岗位
		List<Station> stationList = stationService.getByIds(stationIds);
		model.setStations(new HashSet<Station>(stationList));
		//>>设置新并联岗位的count加1
		for(Station s:stationList){
			s.setCount(s.getCount()+1);
			stationService.update(s);
		}

		//>>设置默认密码为1234（使用MD5摘要）
		model.setPassword(md5Digest);
		//>>创建文件来夹用来放图片
		if(upload != null){//如果upload不为空
		String path = saveUploadFile(upload);
		model.setUser_path(path); 
		}
		//保存到数据库  
		userService.save(model);
	    return "toList";
		}
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
			departmentId_c = user.getDepartment().getId();
		}
		
		if(user.getStations() != null){
			stationIds = new Long[user.getStations().size()];
			int index = 0;
			for(Station station : user.getStations()){
				stationIds[index++] = station.getId();
			}
			
			//保存数据并带到jsp页面中，再带回
			stationIds_c = new Long[user.getStations().size()];
			
			for(Station station_c : user.getStations()){
				stationIds_c[index_c++] = station_c.getId();
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
		//user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		//>>设置所属部门
		if(departmentId == departmentId_c){
		   user.setDepartment(departmentService.getById(departmentId));
		}else{
			//原先的部门count减一
			if(user.getDepartment() != null){
				Department department_c = departmentService.getById(departmentId_c);
				if(department_c.getCount()>0){
				department_c.setCount(department_c.getCount()-1);
				departmentService.update(department_c);
				}
			} 
				//新的部门加一
				if(departmentId !=null){
					Department department = departmentService.getById(departmentId); 
				//封装到对象中（当model是实体类型时，也可以使用model,但要设置未封装的属性）
				//>>设置所属部门
					user.setDepartment(department);
				//>>设置所属部门的count的值
				department.setCount(department.getCount()+1);
				departmentService.update(department);
			 }
		  
		}
		//>>设置所属岗位
		
		if(stationIds.length !=stationIds_c.length){//>>通过数组的长度判断是否已被修改
				//如果修改了	原先的减一.
					//>>找到原先的岗位
					List<Station> stationList_c = stationService.getByIds(stationIds_c);
					//>>原先的岗位逐个减一
					for(Station sc:stationList_c){
						if(sc.getCount()>0){
						sc.setCount(sc.getCount()-1);
						}
						stationService.update(sc);
					}
					
					//>>设置关联的岗位 加一
					List<Station> stationList = stationService.getByIds(stationIds);
					user.setStations(new HashSet<Station>(stationList));
					//>>设置新的岗位逐个加一
					for(Station s:stationList){
						s.setCount(s.getCount()+1);
						stationService.update(s);
					}
			
			
		}else{//如果岗位的数量一样，就判断其岗位的名称是否一样
		
					for(int i=0;i<stationIds.length;i++){
						if(stationIds_c[i]==stationIds[i]){
							flag=true;//如果一样就
						}else{
							flag=false;//如果有一个不一样就停止
							break;
						}
					}
	
			
					if(flag){//都一样，直接保存
						List<Station> stationList = stationService.getByIds(stationIds);
						user.setStations(new HashSet<Station>(stationList));
					}else{//不一样就
						//
						//>>原先的减一
						List<Station> stationList_c = stationService.getByIds(stationIds_c);
					//	user.setStations(new HashSet<Station>(stationList));
						for(Station sc:stationList_c){
							if(sc.getCount()>0){
							sc.setCount(sc.getCount()-1);
							}
							stationService.update(sc);
						}
						//>>设置关联的岗位 加一
						List<Station> stationList = stationService.getByIds(stationIds);
						//>>设置新并联岗位的count加1
						for(Station s:stationList){
							s.setCount(s.getCount()+1);
							stationService.update(s);
						}
						user.setStations(new HashSet<Station>(stationList));
			}
		}
		//>>创建文件来夹用来放图片
		if(upload != null){//如果upload不为空
		String path = saveUploadFile(upload);
		user.setUser_path(path); 
		}
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
	 * 个人设置---显示个人信息
	 */
	
	public String userInfoUI() throws Exception{
		User person = userService.getById(getCurrentUser().getId());
		ActionContext.getContext().getValueStack().push(person);
		if(person.getStations() != null){
			stationIds = new Long[person.getStations().size()];
			int index = 0;
			for(Station station : person.getStations()){
				stationIds[index++] = station.getId();
			}
			List<Station> stationList = stationService.getByIds(stationIds);
			ActionContext.getContext().put("stationList", stationList);
		}
	/*	//显示图片
		inputStream = new FileInputStream(person.getImg_path());*/
		return "userInfoUI";
	}
	/**
	 * 个人设置---修改头像
	 */
	
	public String userInfo()throws Exception{
	
		//1从数据库中取出原对象
		User user = userService.getById(getCurrentUser().getId());
		if(upload != null){//如果upload不为空
			if(user.getImg_path()!=null){//如果对象的图片路径已存在
				File file = new File(user.getImg_path());
				if(file.exists()){//删除掉
					file.delete();
				}
				
				//使用新文件
				String path = saveUploadFile(upload);
				user.setImg_path(path);
			}else{//如果对象的图片路径不存在
				//创建
				String path = saveUploadFile(upload);
				user.setImg_path(path);
			}
		}
		//更新到数据库
		userService.update(user);
	
		return "toUserInfoUI";
	}
	/**
	 * 个人设置---密码修改
	 */
	public String editPasswordUI()throws Exception{
		return "editPasswordUI";
	}
	/**
	 * 修改密码
	 */
	public String editPassword()throws Exception{
		String oldPasswordMD5 = DigestUtils.md5Hex(oldPassword);//转化为32位的十六进制的数
		String newPasswordMD5 = DigestUtils.md5Hex(newPassword);//转化为32位的十六进制的数
		String newPassword2MD5 = DigestUtils.md5Hex(newPassword2);//转化为32位的十六进制的数
		if(oldPasswordMD5.equals(getCurrentUser().getPassword()) && newPasswordMD5.equals(newPassword2MD5)){
			User user = userService.getById(getCurrentUser().getId());
			user.setPassword(newPasswordMD5);
			userService.update(user);
			return "toUserInfoUI";
		}else{
			addFieldError("login","不符合条件！请重新设置！");
		   return "editPasswordUI";
		}
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public File getUpload() {
		System.out.println("------------------upload:"+upload);
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long[] getStationIds_c() {
		return stationIds_c;
	}

	public void setStationIds_c(Long[] stationIds_c) {
		this.stationIds_c = stationIds_c;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Long getDepartmentId_c() {
		return departmentId_c;
	}

	public void setDepartmentId_c(Long departmentId_c) {
		this.departmentId_c = departmentId_c;
	}

	public int getIndex_c() {
		return index_c;
	}

	public void setIndex_c(int index_c) {
		this.index_c = index_c;
	}

}
