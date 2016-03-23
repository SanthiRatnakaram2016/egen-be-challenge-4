package com.sensor.tests;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.mongodb.BasicDBObject;
import com.sensor.domain.Alert;
import com.sensor.service.AlertAPI;

@SpringBootApplication
@Configuration
@ComponentScan("com.sensor.dao , com.sensor.service")
@ImportResource({"/applicationContext-dao.xml","/applicationContext-rules.xml"})
public class TestAlert {

	public static void main(String[] args) throws UnknownHostException {
		
		
		ApplicationContext ctx = SpringApplication.run(TestAlert.class, args);
		
		AlertAPI alertAPI = (AlertAPI)ctx.getBean("alertAPI");
		alertAPI.getAlertDAO().getDatastore().getCollection(Alert.class).remove(new BasicDBObject());
		
		testAlertSave(alertAPI);
		testAlertRead(alertAPI);
		testAlertReadByRange(alertAPI);
		
		
		SpringApplication.exit(ctx, getExitGenerator());
	}
	
	public static void testAlertSave(AlertAPI alertAPI){
		
		Alert alert1 = new Alert();
		alert1.setBaseWeight(51);
		alert1.setIndicator("Under Weight");
		alert1.setTimeStamp(25L);
		alert1.setValue(36);
		alertAPI.save(alert1);
		
		Alert alert2 = new Alert();
		alert2.setBaseWeight(61);
		alert2.setIndicator("Under Weight");
		alert2.setTimeStamp(35L);
		alert2.setValue(46);
				
		alertAPI.save(alert2);
	}
	
	public static void testAlertRead(AlertAPI alertAPI){
		
		List<Alert> alertList = alertAPI.read();
		System.out.println("testAlertRead : Size of Alert Collection : "+alertList.size());
	}
	
	public static void testAlertReadByRange(AlertAPI alertAPI){
		List<Alert> alertList = alertAPI.readByTimeRange(24L, 35L);
		System.out.println("testAlertReadByRange : Size of Alert Collection : "+alertList.size());
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
