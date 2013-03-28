package com.software.project.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Score {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String userId;
	private Integer levelPlayed;
	private Integer timeSpent;
	private Date datePlayed;
	
	public Score(String userId, Integer levelPlayed, Integer timeSpent) {
		this.userId = userId;
		this.levelPlayed = levelPlayed;
		this.timeSpent = timeSpent;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getLevelPlayed() {
		return levelPlayed;
	}
	public void setLevelPlayed(Integer levelPlayed) {
		this.levelPlayed = levelPlayed;
	}
	public Integer getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(Integer timeSpent) {
		this.timeSpent = timeSpent;
	}
	public Date getDatePlayed() {
		return datePlayed;
	}
	public void setDatePlayed(Date datePlayed) {
		this.datePlayed = datePlayed;
	}

}