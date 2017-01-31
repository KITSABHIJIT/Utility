package com.springBootClient;

public class SpringBootClient {
	private static void createEmployee()
	{
	    final String uri = "http://localhost:8080/springrestexample/employees";
	 
	    Employee newEmployee = new Employee(-1, "Adam", "Gilly", "test@email.com");
	 
	    RestTemplate restTemplate = new RestTemplate();
	    Employee result = restTemplate.postForObject( uri, newEmployee, Employee.class);
	 
	    System.out.println(result);
	}
}
