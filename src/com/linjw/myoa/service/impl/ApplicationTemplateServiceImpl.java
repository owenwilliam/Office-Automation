package com.linjw.myoa.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import com.linjw.myoa.base.DaoSupportImpl;
import com.linjw.myoa.model.ApplicationTemplate;
import com.linjw.myoa.service.ApplicationTemplateService;
@Service
public class ApplicationTemplateServiceImpl extends DaoSupportImpl<ApplicationTemplate>implements ApplicationTemplateService {

	@Override
	public void delete(Long id) {
		//删除数据库记录
		ApplicationTemplate applicationTemplate = getById(id);
		getSession().delete(applicationTemplate);
		
		//删除文件夹下的文件
		File file = new File(applicationTemplate.getPath());
		if (file.exists()) {
			file.delete();
		}
		
	}

}
