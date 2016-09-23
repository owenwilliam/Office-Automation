package com.linjw.myoa.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.linjw.myoa.base.DaoSupportImpl;

import com.linjw.myoa.model.NoticeInfo;
import com.linjw.myoa.service.NoticeInfoService;
@Service
public class NoticeServiceImpl extends DaoSupportImpl<NoticeInfo> implements NoticeInfoService{

	@SuppressWarnings("unchecked")
	public List<NoticeInfo> getByRoll() {
		
		return getSession().createQuery//
				("FROM NoticeInfo n WHERE n.roll =?")
				.setParameter(0,true).list();
	}
	
	
	
}
