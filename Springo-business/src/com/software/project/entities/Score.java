package com.software.project.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQuery(name="getAllScoresByLevel", query="SELECT s FROM Score s WHERE s.levelPlayed = :level ORDER BY s.timeSpent ASC FETCH FIRST 5 ROWS ONLY")

@Entity
public class Score {
	
	public Score(String userName, Integer levelPlayed, Float timeSpent) {
		this.userName = userName;
		this.levelPlayed = levelPlayed;
		this.timeSpent = timeSpent;
		this.datePlayed = new Date();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private Integer levelPlayed;
	private Float timeSpent;
	@Temporal(value=TemporalType.DATE)
	private Date datePlayed;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getLevelPlayed() {
		return levelPlayed;
	}
	public void setLevelPlayed(Integer levelPlayed) {
		this.levelPlayed = levelPlayed;
	}
	public Float getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(Float timeSpent) {
		this.timeSpent = timeSpent;
	}
	public Date getDatePlayed() {
		return datePlayed;
	}
	public void setDatePlayed(Date datePlayed) {
		this.datePlayed = datePlayed;
	}
	
}