package com.sensor.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sensor.domain.SensorDomain;

@Repository("SensorDAO")
public interface SensorDAO {

	public abstract List<? extends SensorDomain> read();
	public abstract List<? extends SensorDomain> readByTimeRange(Long startTime, Long endTime);
	
}
