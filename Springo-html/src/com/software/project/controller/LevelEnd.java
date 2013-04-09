package com.software.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.software.project.dao.ScoreBoardManager;
import com.software.project.dao.ScoreBoardManagerImpl;
import com.software.project.entities.Score;
import com.software.project.utils.CommandHelper;

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
			
			float currentTimeSpent = Float.valueOf(CommandHelper.clearParameter(request.getParameter("time")));
			
			for (Score score : scores) {

				if (currentTimeSpent < score.getTimeSpent().floatValue()) {
				
					response.getWriter().println(GAME_USER_REQUESTED);
					break;
				}
			}			
			
		}
		
	}

}