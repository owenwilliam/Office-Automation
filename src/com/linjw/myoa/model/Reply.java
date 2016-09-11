package com.linjw.myoa.model;
/**
 * 回复
 * @author 林剑文　2014-7-13
 *
 */
public class Reply extends Article{
        private Topic topic;//所属主题

		public Topic getTopic() {
			return topic;
		}

		public void setTopic(Topic topic) {
			this.topic = topic;
		}
}
