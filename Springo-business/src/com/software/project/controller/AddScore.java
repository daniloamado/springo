package com.software.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.software.project.dao.ScoreBoardManager;
import com.software.project.dao.ScoreBoardManagerImpl;
import com.software.project.entities.Score;

public class AddScore implements Command {

	private ScoreBoardManager scoreBoardManager = new ScoreBoardManagerImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		scoreBoardManager.addScore(new Score(request.getParameter("user"), 
				Integer.valueOf(request.getParameter("level")),
						Float.valueOf(request.getParameter("time"))));
		
	}

}
