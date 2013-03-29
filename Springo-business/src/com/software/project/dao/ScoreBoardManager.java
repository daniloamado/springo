package com.software.project.dao;

import java.util.List;

import com.software.project.entities.Score;
import com.software.project.exceptions.SpringoException;

public interface ScoreBoardManager {
	
	public Score addScore(Score score);
	
	public Score deleteScore(Long id) throws SpringoException;
	
	public List<Score> getAllScoresByLevel(Integer level);
	

}
