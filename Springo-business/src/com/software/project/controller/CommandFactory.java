package com.software.project.controller;

public abstract class CommandFactory {
	
	public static Command getCommand(String cmd) {
		
		try {
		
			return (Command) Class.forName(Command.class.getPackage().getName() + "." + cmd).newInstance();
		
		} catch (Exception e) {
			return null;
		}
	}
}
