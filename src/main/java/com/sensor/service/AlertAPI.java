package com.sensor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensor.dao.AlertDAO;
import com.sensor.domain.Alert;

@Service("alertAPI")
public class AlertAPI implements SensorAPI<Alert>{
	
	@Autowired
	private AlertDAO alertDAO;

	public AlertDAO getAlertDAO() {
		return alertDAO;
	}

	public void setAlertDAO(AlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}

	@Override
	public List<Alert> read() {
		return alertDAO.read();
	}

	@Override
	public List<Alert> readByTimeRange(Long startTime, Long endTime) {
		return alertDAO.readByTimeRange(startTime, endTime);
	}

	@Override
	public void save(Alert alert) {
		alertDAO.save(alert);
		
	}

}
