package com.sensor.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("sensorAPI")
public interface SensorAPI<T> {

	/*public void save(T sensorDomain);
	public List<? extends SensorDomain> read();
	public List<? extends SensorDomain> readByTimeRange(Long startTime, Long endTime);*/
	
	public void save(T sensorDomain);
	public List<T> read();
	public List<T> readByTimeRange(Long startTime, Long endTime);
}
