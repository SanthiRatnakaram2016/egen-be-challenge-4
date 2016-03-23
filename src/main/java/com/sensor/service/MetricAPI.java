package com.sensor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensor.dao.MetricDAO;
import com.sensor.domain.Metric;

@Service("metricAPI")
public class MetricAPI implements SensorAPI<Metric>{

	@Autowired
	private MetricDAO metricDAO;

	public MetricDAO getMetricDAO() {
		return metricDAO;
	}

	public void setMetricDAO(MetricDAO metricDAO) {
		this.metricDAO = metricDAO;
	}
	
	public void create( Metric metric ){
		//Rule Logic
	}

	@Override
	public List<Metric> read() {
		return this.metricDAO.read();
	}

	@Override
	public List<Metric> readByTimeRange(Long startTime, Long endTime) {
		return this.metricDAO.readByTimeRange(startTime, endTime);
	}

	@Override
	public void save(Metric metric) {
		this.metricDAO.save(metric);
		
	}
}
