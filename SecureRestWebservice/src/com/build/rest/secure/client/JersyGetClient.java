package com.build.rest.secure.client;


import org.apache.commons.codec.binary.Base64;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JersyGetClient {
	
	
	public static void main(String[] args)
	{
		/*httpGETCollectionExample();
		httpGETEntityExample();
		httpPOSTMethodExample();
		httpPUTMethodExample();
		httpDELETEMethodExample();*/
		callService();
	}
	
	/*private static void httpGETCollectionExample() 
	{
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
																    .nonPreemptive()
																    .credentials("user", "password")
																    .build();

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(feature) ;

		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget webTarget = client.target("http://localhost:8080/SecureRestWebservice/rest/").path("employees");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		Employees employees = response.readEntity(Employees.class);
		List<Employee> listOfEmployees = employees.getEmployeeList();
			
		System.out.println(response.getStatus());
		System.out.println(Arrays.toString( listOfEmployees.toArray(new Employee[listOfEmployees.size()]) ));
	}
	
	private static void httpGETEntityExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8080/SecureRestWebservice/rest/").path("employees").path("1");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		Employee employee = response.readEntity(Employee.class);
			
		System.out.println(response.getStatus());
		System.out.println(employee);
	}

	private static void httpPOSTMethodExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8080/SecureRestWebservice/rest/").path("employees");
		
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("David Feezor");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(Entity.entity(emp, MediaType.APPLICATION_XML));
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	
	private static void httpPUTMethodExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8080/SecureRestWebservice/rest/").path("employees").path("1");
		
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("David Feezor");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(Entity.entity(emp, MediaType.APPLICATION_XML));
		
		Employee employee = response.readEntity(Employee.class);
			
		System.out.println(response.getStatus());
		System.out.println(employee);
	}
	
	private static void httpDELETEMethodExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8080/SecureRestWebservice/rest/").path("employees").path("1");
		
		Invocation.Builder invocationBuilder =	webTarget.request();
		Response response = invocationBuilder.delete();
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}*/
	
	public static void callService(){
		try {  
			String url = "http://localhost:8080/SecureRestWebservice/rest/employees/1";
			String name = "abc";
			String password = "password";
			String authString = name + ":" + password;
			Base64 base64 = new Base64();
			String authStringEnc = new String(base64.encode(authString.getBytes()));
			System.out.println("Base64 encoded auth string: " + authStringEnc);
			Client restClient = Client.create();
			WebResource webResource = restClient.resource(url);
			ClientResponse resp = webResource.accept("application/json")
					.header("Authorization", "Basic " + authStringEnc)
					.get(ClientResponse.class);
			if(resp.getStatus() != 200){
				String error = resp.getEntity(String.class);
				System.err.println("Error Code: "+resp.getStatus()+" Error Message: "+error);
			}else{
				String output = resp.getEntity(String.class);
				System.out.println("Response Code: "+resp.getStatus()+" Response: "+output);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}