package com.software.project.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.software.project.dao.ScoreBoardManager;
import com.software.project.dao.ScoreBoardManagerImpl;
import com.software.project.entities.Score;

public class RetrieveAll implements Command {

	private ScoreBoardManager scoreBoardManager = new ScoreBoardManagerImpl();
	private int numberOfLevels = 12;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//response format example : "1|Danilo|10.5|10/10/2012;1|Danilo|10.5|10/10/2012--1|Danilo|10.5|10/10/2012;1|Danilo|10.5|10/10/2012";
		
		StringBuilder scores = new StringBuilder();
		
		for (int i = 1; i <= numberOfLevels; i++) {
			 List<Score> scoresByLevel = scoreBoardManager.getAllScoresByLevel(i);
			 for (Score s : scoresByLevel) {
				 scores.append(s.getLevelPlayed() + "|");
				 scores.append(s.getUserName() + "|");
				 scores.append(s.getTimeSpent() + "|");
				 scores.append(DateFormat.getDateInstance(DateFormat.LONG).format(s.getDatePlayed()) + ";");
			 }
			 scores.append("--");
		}
		
		response.getWriter().print(scores.toString());
		
	}

}