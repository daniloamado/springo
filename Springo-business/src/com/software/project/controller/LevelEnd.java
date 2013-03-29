package com.software.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.software.project.dao.ScoreBoardManager;
import com.software.project.dao.ScoreBoardManagerImpl;
import com.software.project.entities.Score;

public class LevelEnd implements Command {
	
	private final String GAME_USER_REQUESTED = "6";
	private ScoreBoardManager scoreBoardManager = new ScoreBoardManagerImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Score> scores = scoreBoardManager.getAllScoresByLevel(Integer.valueOf(request.getParameter("level")));
		
		if (scores.size() < 5) {
			
			response.getWriter().println(GAME_USER_REQUESTED);
		} else {
			
			float currentTimeSpent = Float.valueOf(request.getParameter("time"));
			
			for (int i = 0; i < 5; i++) {
			
				if (currentTimeSpent <= scores.get(i).getTimeSpent().floatValue() && i != 4) {
					
					response.getWriter().println(GAME_USER_REQUESTED);
				}
			}			
			
		}
		
	}

}