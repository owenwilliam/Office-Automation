package com.linjw.myoa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.linjw.myoa.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
/**
 * 审批流程管理
 * @author 林剑文　2014-7-20
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ProcessDefinitionAction extends BaseAction{
	
	private String key;
	private String id;
	
	private InputStream inputStream;//下载文件用的
	private File upload;//上传文件用的

	/**
	 * 列表
	 */
	public String list() throws Exception {
		List<ProcessDefinition> processDefinitionList = processDefinitionService.finAllLatestVersions();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
		return "list";
	}

	/**
	 * 删除
	 */
	public String delete() throws Exception {
		key = URLDecoder.decode(key,"utf-8");//自己再进行一次URL编码
		processDefinitionService.deleteByKey(key);
		return "toList";
	}

	/**
	 * 部署页面
	 */
	public String addUI() throws Exception {
		
		  return "addUI";
	}

	/**
	 * 部署
	 */
	public String add() throws Exception {
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(upload));
		try{
		  processDefinitionService.deploy(zipInputStream);
		}finally{
			zipInputStream.close();
		}
		return "toList";
	}

	/**
	 * 查看流程图
	 */
	public String downloadProcessImage() throws Exception {
		id = URLDecoder.decode(id,"utf-8");//自己再进行一次URL编码
	    inputStream = processDefinitionService.getProcessImafgeResourceAsStream(id);
		return "downloadProcessImage";
	}

	
	//------
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
