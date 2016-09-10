package com.linjw.myoa.service;

import com.linjw.myoa.base.DaoSupport;
import com.linjw.myoa.model.Forum;

public interface ForumService extends DaoSupport<Forum>{

	public void moveUp(Long id);

	public void moveDown(Long id);

}
