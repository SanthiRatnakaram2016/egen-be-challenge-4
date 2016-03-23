package com.sensor.domain;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Range {

	private Long startTime;
	private Long endTime;
	
	public Long getEndTime() {
		return endTime;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
}
