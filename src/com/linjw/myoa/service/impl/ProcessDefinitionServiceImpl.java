package com.linjw.myoa.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linjw.myoa.service.ProcessDefinitionService;
@Service
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
	   @Resource
       private ProcessEngine processEngine;
	   /**列表，由jbpm来的*/
	public List<ProcessDefinition> finAllLatestVersions() {
         //1.查询所有的流程定义列表，把最新的版本都排到最后面
		List<ProcessDefinition> all = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)//按版本排序
				.list();
		//2.过滤出所有的最新版本，使用map的方法，栈的理论，拿到最新的
		Map<String,ProcessDefinition> map = new HashMap<String,ProcessDefinition>();
		for(ProcessDefinition pd : all){
			map.put(pd.getKey(),pd);
		}
		return new ArrayList<ProcessDefinition>(map.values());
	}
	/**删除*/
	public void deleteByKey(String key) {
		//1.查询出指定key（名称）的所有版本流程定义
		List<ProcessDefinition> list = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.processDefinitionKey(key)//
				.list();
		//2.循环删除（指定的名称的所有Id）
		for(ProcessDefinition pd : list){
			processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());
		}
		
	}
	/**部署*/
	public void deploy(ZipInputStream zipInputStream) {
		processEngine.getRepositoryService()//
		.createDeployment()//
		.addResourcesFromZipInputStream(zipInputStream)
		.deploy();
	}
	/**查看流程图*/
	public InputStream getProcessImafgeResourceAsStream(String processDefinitionId) {
		// 根据id取出对应的流程定义对象
		ProcessDefinition pd = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.processDefinitionId(processDefinitionId)//
				.uniqueResult();
		//返回图片资源
		return processEngine.getRepositoryService().getResourceAsStream(pd.getDeploymentId(), pd.getImageResourceName());
	}


}
