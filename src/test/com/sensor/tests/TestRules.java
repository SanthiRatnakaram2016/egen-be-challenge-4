package com.sensor.tests;

import java.net.UnknownHostException;

import org.easyrules.api.RulesEngine;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.sensor.domain.Alert;
import com.sensor.rules.OverWeightRule;
import com.sensor.rules.UnderWeightRule;

@SpringBootApplication
@Configuration
@ComponentScan("com.sensor.dao , com.sensor.service , com.sensor.rules")
@ImportResource({"/applicationContext-dao.xml","/applicationContext-rules.xml"})
public class TestRules {

	public static void main(String[] args) throws UnknownHostException {
		
		ApplicationContext ctx = SpringApplication.run(TestRules.class, args);
		UnderWeightRule underWeightRule = (UnderWeightRule)ctx.getBean("underWeightRule");
		OverWeightRule overWeightRule = (OverWeightRule)ctx.getBean("overWeightRule");
		
		RulesEngine rulesEngine = (RulesEngine)ctx.getBean("rulesEngine");
		
		testOverWeight(underWeightRule,overWeightRule,rulesEngine);
		testUnderWeight(underWeightRule,overWeightRule,rulesEngine);
		
		SpringApplication.exit(ctx, getExitGenerator());
	}
	
	public static void testOverWeight(UnderWeightRule underWeightRule , OverWeightRule overWeightRule, RulesEngine rulesEngine){
		Alert alert = new Alert();
		alert.setValue(120);
		alert.setBaseWeight(100);
		
		overWeightRule.setAlert(alert);
		underWeightRule.setAlert(alert);
		
		rulesEngine.fireRules();
		
		System.out.println("testOverWeight--->"+alert.getIndicator());
	}
	
	public static void testUnderWeight(UnderWeightRule underWeightRule , OverWeightRule overWeightRule, RulesEngine rulesEngine){
		Alert alert = new Alert();
		alert.setValue(5);
		alert.setBaseWeight(120);
		
		overWeightRule.setAlert(alert);
		underWeightRule.setAlert(alert);
		
		rulesEngine.fireRules();
		
		System.out.println("testUnderWeight--->"+alert.getIndicator());
	}

	public static ExitCodeGenerator getExitGenerator(){
		  ExitCodeGenerator exitCodeGenerator = new ExitCodeGenerator() {
		    @Override
		    public int getExitCode() {
		      return 0;
		    }
		  };
		  return exitCodeGenerator;
	}
}
