package com.software.project.utils;

public class ScoreHelper {
	
	private String level;
	private String player;
	private String time;
	private String date;
	
	public ScoreHelper(String level, String player, String time, String date) {
		
		this.level = level;
		this.player = player;
		this.time = time;
		this.date = date;
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}