package com.test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.model.AuthenticationService;


public class LoginController extends HttpServlet {

	private String userName;
	private String password;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		userName = request.getParameter("userName");
		password = request.getParameter("password");

		if ((userName != null && !userName.isEmpty()) && password != null
				&& !password.isEmpty()) {

			AuthenticationService service = new AuthenticationService();
			
			boolean status = service.verifyUser(userName, password);
			
			if(status) {
			
				RequestDispatcher dispatcher = request.getRequestDispatcher("success.html");
				dispatcher.forward(request, response);
				
			} else {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.html");
				dispatcher.forward(request, response);
			}
		} else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.html");
			dispatcher.forward(request, response);
		}
		
	}

}
