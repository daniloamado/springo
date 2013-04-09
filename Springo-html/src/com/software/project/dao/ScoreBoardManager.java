package com.software.project.dao;

import java.util.List;

import com.software.project.entities.Score;

public interface ScoreBoardManager {
	
	public Score addScore(Score score);
	
	public List<Score> getAllScoresByLevel(Integer level);
	
}
