package com.software.project.controller;

import com.software.project.utils.CommandHelper;

public abstract class CommandFactory {
	
	public static Command getCommand(String cmd) throws Exception{
		
		try {
		
			return (Command) Class.forName(Command.class.getPackage().getName() + "." + CommandHelper.clearParameter(cmd)).newInstance();
		
		} catch (Exception e) {
			throw e;
		}
	}
	
}