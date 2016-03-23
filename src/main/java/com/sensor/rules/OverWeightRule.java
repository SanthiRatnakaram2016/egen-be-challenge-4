package com.sensor.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.sensor.domain.Alert;

@Rule(name = "overWeightRule")
public class OverWeightRule {
	
	private Alert alert;
	
	/*public OverWeightRule(Alert alert) {
        super("OverWeightRule", "Detects if the weight is over-weight", 2);
        this.alert = alert;
    }*/

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	@Condition
    public boolean isOverWeight() {
		return this.alert.getValue() > 1.10 * alert.getBaseWeight() ;
    }

	@Action(order = 2)
    public void markAsOverWeight(){
		this.alert.setIndicator("Over Weight");
		System.out.println("Inside markAsOverWeight...");
    }
	
}
