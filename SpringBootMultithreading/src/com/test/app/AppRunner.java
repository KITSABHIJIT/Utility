package com.test.app;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AppRunner implements Runnable{

	String name;

	public void setName(String name){
		this.name = name;
	}

	@Override
	public void run() {

		System.out.println(name + " is Started");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(name + " is Ending");

	}

}