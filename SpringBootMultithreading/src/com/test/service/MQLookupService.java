package com.test.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.test.app.AppRunner;
import com.test.app.Application;


public class MQLookupService {
  public static void main(String[] args) {

    ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
    ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");

    AppRunner printTask1 = (AppRunner) context.getBean("appRunner");
    printTask1.setName("Thread 1");
    taskExecutor.execute(printTask1);

    AppRunner printTask2 = (AppRunner) context.getBean("appRunner");
    printTask2.setName("Thread 2");
    taskExecutor.execute(printTask2);

    AppRunner printTask3 = (AppRunner) context.getBean("appRunner");
    printTask3.setName("Thread 3");
    taskExecutor.execute(printTask3);
    
    AppRunner printTask4 = (AppRunner) context.getBean("appRunner");
    printTask4.setName("Thread 4");
    taskExecutor.execute(printTask4);
    
    AppRunner printTask5 = (AppRunner) context.getBean("appRunner");
    printTask5.setName("Thread 5");
    taskExecutor.execute(printTask5);
    
    AppRunner printTask6 = (AppRunner) context.getBean("appRunner");
    printTask6.setName("Thread 6");
    taskExecutor.execute(printTask6);

	while(true) {
		int count = taskExecutor.getActiveCount();
		System.out.println("Active Threads : " + count);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (count == 0) {
			taskExecutor.shutdown();
			break;
		}
	}

   }
}