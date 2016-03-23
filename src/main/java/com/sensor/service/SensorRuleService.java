package com.sensor.service;

import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sensor.domain.Alert;
import com.sensor.domain.Metric;
import com.sensor.rules.OverWeightRule;
import com.sensor.rules.UnderWeightRule;

@Service("SensorRuleService")
public class SensorRuleService {
	
	@Autowired
	private RulesEngine rulesEngine;

	@Autowired
	private UnderWeightRule underWeightRule;
	
	@Autowired
	private OverWeightRule overWeightRule;

	public void setRulesEngine(RulesEngine rulesEngine) {
		this.rulesEngine = rulesEngine;
	}
	
	public void setUnderWeightRule(UnderWeightRule underWeightRule) {
		this.underWeightRule = underWeightRule;
	}

	public void setOverWeightRule(OverWeightRule overWeightRule) {
		this.overWeightRule = overWeightRule;
	}

	public Alert executeRules(Metric metric){
		
		Alert alert = null;
		
		if(null != metric){
			alert = new Alert();
			alert.setBaseWeight(metric.getBaseWeight());
			alert.setValue(metric.getValue());
			alert.setTimeStamp(metric.getTimeStamp());
			
			underWeightRule.setAlert(alert);
			overWeightRule.setAlert(alert);
			rulesEngine.fireRules();
		}
		
		return alert;
		
	}
}
