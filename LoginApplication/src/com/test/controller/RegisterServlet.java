package com.test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.entity.UserDetails;
import com.test.service.RegistrationService;


public class RegisterServlet extends HttpServlet {

	private String userName = null;
	private String password = null;
	private String firstName = null;
	private String lastName = null;
	private String email = null;
	private String phone = null;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// getting the input from user
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		email = request.getParameter("email");
		phone = request.getParameter("phone");
		
		// simple validation
		if((userName != null && !userName.isEmpty()) 
				&& (password != null && !password.isEmpty())
				&& (firstName != null && !firstName.isEmpty())
				&& (lastName != null && !lastName.isEmpty())
				&& (email != null && !email.isEmpty())
				&& (phone != null && !phone.isEmpty())) {
			
			// defining the object for AuthenticationService
			RegistrationService service = new RegistrationService();
			UserDetails details=new UserDetails();
			details.setUserName(userName);
			details.setPassword(password);
			details.setFirstName(firstName);
			details.setLastName(lastName);
			details.setEmail(email);
			details.setPhone(phone);
			
			// validating the user input
			UserDetails userDetails = service.registerUser(details);
			
			// dispatching the result based on the outcome 
			if(userDetails != null && userDetails.isValidUser()) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				
				// setting error message
				request.setAttribute("errorMessage", "User Registered Succussfully. Plase login ");
				
				dispatcher.forward(request, response);
				
				
			} else {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
				
				// setting error message
				request.setAttribute("errorMessage", "User Registration failed. try again ");
				
				dispatcher.forward(request, response);
				
			}
			
		} else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			
			// setting error message
			request.setAttribute("errorMessage", "please make sure all the fields are not empty :( ");
			
			dispatcher.forward(request, response);
			
		}
	}
}
