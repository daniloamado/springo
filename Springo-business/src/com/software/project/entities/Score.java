package com.software.project.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Score {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private Integer levelPlayed;
	private Float timeSpent;
	@Temporal(value=TemporalType.DATE)
	private Date dataPlayed;
	
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
	public Date getDataPlayed() {
		return dataPlayed;
	}
	public void setDataPlayed(Date dataPlayed) {
		this.dataPlayed = dataPlayed;
	}
	
}