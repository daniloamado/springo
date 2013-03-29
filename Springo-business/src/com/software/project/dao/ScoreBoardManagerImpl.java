package com.software.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.software.project.entities.Score;
import com.software.project.exceptions.SpringoException;

public class ScoreBoardManagerImpl implements ScoreBoardManager {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("transactions-optional");
	private EntityManager em;

	@Override
	public Score addScore(Score score) {
		
		em = factory.createEntityManager();
		em.persist(score);
		em.close();
		return score;
	}

	@Override
	public Score deleteScore(Long id) throws SpringoException {
		return null;
		
	}

	@Override
	public List<Score> getAllScoresByLevel(Integer level) {
		
		em = factory.createEntityManager();
		TypedQuery<Score> query = em.createNamedQuery("getAllScoresByLevel", Score.class);
		query.setParameter("level", level);
		List<Score> scores = query.getResultList();
		em.close();
		return scores; 
	}

}