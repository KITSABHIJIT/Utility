package com.test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.entity.UserDetails;
import com.test.service.AuthenticationService;


public class LoginServlet extends HttpServlet {

	private String userName = null;
	private String password = null;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// getting the input from user
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		
		// simple validation
		if((userName != null && !userName.isEmpty()) && (password != null && !password.isEmpty())) {
			
			// defining the object for AuthenticationService
			AuthenticationService service = new AuthenticationService();
			
			// validating the user input
			UserDetails userDetails = service.validateUser(userName, password);
			
			// dispatching the result based on the outcome 
			if(userDetails != null && userDetails.isValidUser()) {
				
				HttpSession session = request.getSession();
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
				
				// setting user details object in session based on the valid outcome
				session.setAttribute("userDetails", userDetails);
				
				dispatcher.forward(request, response);
				
				
			} else {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				
				// setting error message
				request.setAttribute("errorMessage", "you have given invalid username or password :( ");
				
				dispatcher.forward(request, response);
				
			}
			
		} else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			
			// setting error message
			request.setAttribute("errorMessage", "please make sure username or password is not empty :( ");
			
			dispatcher.forward(request, response);
			
		}
	}
}
