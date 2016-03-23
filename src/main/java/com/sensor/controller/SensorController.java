package com.sensor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensor.domain.Alert;
import com.sensor.domain.Metric;
import com.sensor.domain.Range;
import com.sensor.service.AlertAPI;
import com.sensor.service.MetricAPI;
import com.sensor.service.SensorRuleService;

@Controller
@RequestMapping("/sensor")
public class SensorController{

	@Autowired
	private MetricAPI metricAPI;
	
	@Autowired
	private AlertAPI alertAPI;
	
	public void setAlertAPI(AlertAPI alertAPI) {
		this.alertAPI = alertAPI;
	}

	@Autowired
	private SensorRuleService sensorRuleService;
	
	
	
	public void setSensorRuleService(SensorRuleService sensorRuleService) {
		this.sensorRuleService = sensorRuleService;
	}

	public void setMetricAPI(MetricAPI metricAPI) {
		this.metricAPI = metricAPI;
	}

	
	@RequestMapping(value="/create",method=RequestMethod.POST, headers = {"Content-type=application/json"})
    public @ResponseBody Metric create(@RequestBody(required=false) Metric metric) {
       
	   if(null == metric.getBaseWeight()){
		   metric.setBaseWeight(50);
	   }
	   
	   if(null == metric.getTimeStamp()){
		   metric.setTimeStamp(System.currentTimeMillis());
	   }
	   
       metricAPI.save(metric);
       System.out.println("metric-->"+metric);
       
       Alert alert = sensorRuleService.executeRules(metric);
       
       if(null != alert.getIndicator()){
    	   alertAPI.save(alert);
       }
       return metric;
    }
	
	@RequestMapping(value="/metrics", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Metric> readMetrics() {
		
		List<Metric> metrics = metricAPI.read();
		return metrics;
    }
	
	
	@RequestMapping(value="/metrics/range", method=RequestMethod.POST, produces = "application/json", consumes="application/json", headers = {"Content-type=application/json"})
    public @ResponseBody List<Metric> readMetricsByTimeRange(@RequestBody Range range) {
		
		List<Metric> metrics = metricAPI.readByTimeRange(range.getStartTime(), range.getEndTime());
		return metrics;
    }
	
	@RequestMapping(value="/alerts/range", method=RequestMethod.POST, produces = "application/json", consumes="application/json")
    public @ResponseBody List<Alert> readAlertsByTimeRange(@RequestBody Range range) {
		List<Alert> alerts = alertAPI.readByTimeRange(range.getStartTime(), range.getEndTime());
		//ObjectMapper mapper = new ObjectMapper();
		return alerts;
    }
	
	@RequestMapping(value="/alerts", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Alert> readAlerts() {
		List<Alert> alerts = alertAPI.read();
		return alerts;
		
    }
}
