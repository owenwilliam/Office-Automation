package com.linjw.myoa.service;

import java.util.List;

import com.linjw.myoa.base.DaoSupport;
import com.linjw.myoa.model.NoticeInfo;

public interface NoticeInfoService extends DaoSupport<NoticeInfo> {

	public List<NoticeInfo> getByRoll();

}
