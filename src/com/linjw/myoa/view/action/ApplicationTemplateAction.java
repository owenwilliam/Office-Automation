package com.linjw.myoa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.ModelDrivenBaseAction;
import com.linjw.myoa.model.ApplicationTemplate;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ApplicationTemplateAction extends ModelDrivenBaseAction<ApplicationTemplate>{
	
	private File upload;//上传的文件
	private InputStream inputStream;//下载用的
	/** 列表 */
	public String list() throws Exception {
		List<ApplicationTemplate> applicationTemplateList = applicationTemplateService.findAll();
		ActionContext.getContext().put("applicationTemplateList", applicationTemplateList);
		return "list";
	}

	/** 删除,也删除掉文件夹中的 */
	public String delete() throws Exception {
		applicationTemplateService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		//准备数据
		List<ProcessDefinition> processDefinitionList = processDefinitionService.finAllLatestVersions();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
		return "addUI";
	}

	/** 添加 */
	public String add() throws Exception {
		//封装
		String path = saveUploadFile(upload);
		model.setPath(path);
		//保存
		applicationTemplateService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		//准备数据
		List<ProcessDefinition> processDefinitionList = processDefinitionService.finAllLatestVersions();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
		//准备回显数据
		ApplicationTemplate applicationTemplate = applicationTemplateService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(applicationTemplate);
		return "editUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		//1.从数据库中取出原对象
		ApplicationTemplate applicationTemplate = applicationTemplateService.getById(model.getId());
		
		//2.设置要修改的属性
		applicationTemplate.setName(model.getName());
		applicationTemplate.setProcessDefinition(model.getProcessDefinition());
		
		if(upload != null){//如果上传了文件
			//删除旧文件
			File file = new File(applicationTemplate.getPath());
			if(file.exists()){
				file.delete();
			}
			//使用新文件
			String path = saveUploadFile(upload);
			applicationTemplate.setPath(path);
		}
			//更新到数据库
			applicationTemplateService.update(applicationTemplate);
		
		return "toList";
	}

	/** 下载 */
	public String download() throws Exception {
		//准备下载的资源
		ApplicationTemplate applicationTemplate = applicationTemplateService.getById(model.getId());
		inputStream = new FileInputStream(applicationTemplate.getPath());
		//准备文件名（解决乱码问题）
		String fileName = URLEncoder.encode(applicationTemplate.getName(),"utf-8");
		ActionContext.getContext().put("fileName", fileName);
		return "download";
	}

	
	//-----
	public File getUpload() {
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

}
