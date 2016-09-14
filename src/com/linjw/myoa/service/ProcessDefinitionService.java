package com.linjw.myoa.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;


public interface ProcessDefinitionService {

	public  List<ProcessDefinition>  finAllLatestVersions();  /**查找最新版本的*/

	public void deleteByKey(String key);/**删除*/

	public void deploy(ZipInputStream zipInputStream);/**部署*/

	public InputStream getProcessImafgeResourceAsStream(String processDefinitionId);/**查看流程图*/

}
