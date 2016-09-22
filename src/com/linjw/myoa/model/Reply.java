package com.linjw.myoa.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 回复
 * @author 林剑文　2014-7-13
 *
 */
public class Reply extends Article{
        private Topic topic;//所属主题
       private Topic lastToTopic;

		public Topic getTopic() {
			return topic;
		}

		public void setTopic(Topic topic) {
			this.topic = topic;
		}

		public Topic getLastToTopic() {
			return lastToTopic;
		}

		public void setLastToTopic(Topic lastToTopic) {
			this.lastToTopic = lastToTopic;
		}

		

		

		
}
