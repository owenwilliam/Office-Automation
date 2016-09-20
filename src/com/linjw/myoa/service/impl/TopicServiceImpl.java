package com.linjw.myoa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.linjw.myoa.base.DaoSupportImpl;
import com.linjw.myoa.model.Forum;
import com.linjw.myoa.model.PageBean;
import com.linjw.myoa.model.Topic;
import com.linjw.myoa.service.TopicService;
@Service
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService {
	// 排序：所有置顶帖在最上面，并按最后更新时间排序，让新状态的在上面。
	public List<Topic> findByForum(Forum forum) {
		return getSession().createQuery(
				    "from Topic t where t.forum=? order by (case t.type when 2 then 2 else 0 end) desc, t.lastUpdateTime desc")//
				    .setParameter(0, forum)
				    .list();
	}

	@Override
	public void save(Topic topic) {
		//1,设置属性并保存
	   topic.setType(Topic.TYPE_NORMAL);//默认为普通属性
	   topic.setReplyCount(0);
	   topic.setLastReply(null);
	   topic.setLastUpdateTime(topic.getPostTime());
	   getSession().save(topic);//保存
		//2、维护相关特殊属性
	   
	   Forum forum = topic.getForum();
	   forum.setTopicCount(forum.getTopicCount()+1);//主题数
	   forum.setArticleCount(forum.getArticleCount()+1);// 文章数量（主题数+回复数）
	   forum.setLastTopic(topic); // 最后发表的主题
	   getSession().update(forum);
	}
	
	public void delete(Topic topic) {
		//1.维护相关属性
		
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount()-1);
	forum.setArticleCount(forum.getArticleCount()-topic.getReplyCount()-1);
		getSession().delete(topic);
	}

	
	
	@SuppressWarnings("rawtypes")
	@Deprecated
	public PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum) {

		// 查询本页的数据列表
		List list = getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();

		// 查询总记录数量
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
				.setParameter(0, forum)//
				.uniqueResult();

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}



	
	
	

}
