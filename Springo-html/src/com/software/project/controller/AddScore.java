package com.software.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.software.project.dao.ScoreBoardManager;
import com.software.project.dao.ScoreBoardManagerImpl;
import com.software.project.entities.Score;
import com.software.project.utils.CommandHelper;

public class AddScore implements Command {

	private ScoreBoardManager scoreBoardManager = new ScoreBoardManagerImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		scoreBoardManager.addScore(new Score(CommandHelper.clearParameter(request.getParameter("user")), 
				Integer.valueOf(CommandHelper.clearParameter(request.getParameter("level"))),
						Float.valueOf(CommandHelper.clearParameter(request.getParameter("time")))));
		
	}

}
