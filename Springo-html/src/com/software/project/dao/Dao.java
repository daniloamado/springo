package com.software.project.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public enum Dao {
	instance;
	
	private static final Logger log = Logger.getLogger(Dao.class.getName());
	

	public void add(String userId, Integer levelPlayed, Integer timeSpent) {
		
		log.info("begin add Score");
		
		synchronized(this) {
			EntityManager em = EMFService.get().createEntityManager();
			Score score = new Score(userId, levelPlayed, timeSpent);
			em.persist(score);
			em.close();
		}
		
		log.info("end add Score");
	}
	
	public List<Score> getScores(Integer level) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select s from Score s where s.levelPlayed = :level");
		q.setParameter("level", level);
		List<Score> scores = q.getResultList();
		return scores;
	}	
	
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Score score = em.find(Score.class, id);
			em.remove(score);
		} finally {
			em.close();
		}
	}
}