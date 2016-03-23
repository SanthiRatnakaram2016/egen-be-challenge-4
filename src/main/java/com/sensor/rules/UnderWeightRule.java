package com.sensor.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.sensor.domain.Alert;

@Rule(name = "underWeightRule")
public class UnderWeightRule{
	
	private Alert alert;
	
	/*public UnderWeightRule(Alert alert) {
        super("UnderWeightRule", "Detects if the weight is under-weight", 1);
        this.alert = alert;
    }*/

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	@Condition
    public boolean isUnderWeight() {
		return this.alert.getValue() < 0.1 * this.alert.getBaseWeight() ;
    }

	@Action(order = 1)
    public void markAsUnderWeight(){
    	this.alert.setIndicator("Under Weight");
    	System.out.println("Inside markAsUnderWeight...");
    }
	
}
