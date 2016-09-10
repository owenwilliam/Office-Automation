package com.linjw.myoa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linjw.myoa.base.DaoSupportImpl;
import com.linjw.myoa.model.Forum;
import com.linjw.myoa.service.ForumService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService {

	@Override
	public void save(Forum forum) {
		// 保存
		super.save(forum);
		// 设置position的值
		forum.setPosition(forum.getId().intValue());
	}


	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("from Forum f order by f.position").list();
	}


	public void moveUp(Long id) {
		// 找出相关的Forum
          Forum forum = getById(id);// 当前要移动的Forum
          Forum other = (Forum)getSession().createQuery(// 我上面的那个Forum
        		  "from Forum f where f.position<? order by f.position desc")//
        		  .setParameter(0,forum.getPosition())//
        		  .setFirstResult(0)
        		  .setMaxResults(1)
        		  .uniqueResult();
          //最上面的不能移
          if(other == null){
        	  return;
          }
        //交换position的值
          int temp = forum.getPosition();
          forum.setPosition(other.getPosition());
          other.setPosition(temp);
          //更新到数据中（可以不写，因为对象现在是在持久化状态）
          getSession().update(forum);
          getSession().update(other);
          
          
	}


	public void moveDown(Long id) {
		// 找出相关的Forum
		   Forum forum = getById(id);// 当前要移动的Forum
	          Forum other = (Forum)getSession().createQuery(// 我上面的那个Forum
	        		  "from Forum f where f.position>? order by f.position asc")//
	        		  .setParameter(0,forum.getPosition())//
	        		  .setFirstResult(0)
	        		  .setMaxResults(1)
	        		  .uniqueResult();
	          //最上面的不能移
	          if(other == null){
	        	  return;
	          }
	        //交换position的值
	          int temp = forum.getPosition();
	          forum.setPosition(other.getPosition());
	          other.setPosition(temp);
	          //更新到数据中（可以不写，因为对象现在是在持久化状态）
	          getSession().update(forum);
	          getSession().update(other);
		
	}

	
	
}
