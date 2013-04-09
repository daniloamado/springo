package com.software.project.utils;

public class CommandHelper {

	public static String clearParameter(String cmd) {
		if (cmd.indexOf('?') != -1) {
			return cmd.substring(0, cmd.indexOf('?'));
		}
		return cmd;
	}
	
}
