package com.software.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ScoreServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("testing...");
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if (user == null) {
			String loginURL = userService.createLoginURL(request.getRequestURL().toString());
			request.getRequestDispatcher(loginURL).forward(request, response);
		} else {
			response.getWriter().print(user.getUserId() + " Score Servlet - Level: ");	
		}
		
	}

}