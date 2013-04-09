package com.software.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller {

	private Command command;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public Controller(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void process() throws ServletException, IOException {
		this.command.execute(request, response);
	}
	
}