package mywebapp;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestQuartz {
	@Test
	public void testTask() throws Exception {
		System.out.println("test start!"); 
		ApplicationContext context = new ClassPathXmlApplicationContext("config/quartz.xml"); 
		System.out.println("test end!"); 
	}
}
