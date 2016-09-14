package com.linjw.myoa.model;
/**
 * 审批流转：表单模板管理
 * @author 林剑文　2014-7-19
 *
 */
public class ApplicationTemplate {

	private Long id;
	private String name;
	private String processDefinition;//所用到的流程
	private String path;//文件在服务器端存储的路径
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcessDefinition() {
		return processDefinition;
	}
	public void setProcessDefinition(String processDefinition) {
		this.processDefinition = processDefinition;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
