package com.build.maven.simple;

import org.springframework.web.client.RestTemplate;

public class SpringBootClient {
	private static void getEmployees()
	{
	    final String uri = "http://localhost:8080/employees.json";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	     
	    System.out.println(result);
	}
	
	public static void main (String ...strings){
		SpringBootClient.getEmployees();
	}
}
