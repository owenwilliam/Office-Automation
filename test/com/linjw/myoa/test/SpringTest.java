package com.linjw.myoa.test;

import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void testBean()throws Exception{
		TestAction testAction = (TestAction)ac.getBean("testAction");
		System.out.println(testAction);
	}
	// 测试SessionFactory
	@Test
	public void testSessionFactory()throws Exception{
		SessionFactory sessionFactory = (SessionFactory)ac.getBean("sessionFactory");
	   System.out.println(sessionFactory);
	}
	
	
	// 测试processEngine
		@Test
		public void testprocessEngineFactory()throws Exception{
			ProcessEngine processEngine = (ProcessEngine)ac.getBean("processEngine");
		   System.out.println(processEngine);
		}
	//测试事务
	@Test
	public void testTransaction()throws Exception{
		TestService testService = (TestService) ac.getBean("testService");
		testService.saveTwoUsers();
	}

}
