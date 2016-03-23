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
import com.sensor.domain.Metric;
import com.sensor.service.MetricAPI;

@SpringBootApplication
@Configuration
@ComponentScan("com.sensor.dao , com.sensor.service")
@ImportResource({"/applicationContext-dao.xml","/applicationContext-rules.xml"})
public class TestMetric {

	public static void main(String[] args) throws UnknownHostException {
		
		
		ApplicationContext ctx = SpringApplication.run(TestMetric.class, args);
		
		MetricAPI metricAPI = (MetricAPI)ctx.getBean("metricAPI");
		metricAPI.getMetricDAO().getDatastore().getCollection(Metric.class).remove(new BasicDBObject());
		
		testMetricSave(metricAPI);
		testMetricRead(metricAPI);
		testMetricReadByRange(metricAPI);
		
		
		SpringApplication.exit(ctx, getExitGenerator());
	}
	
	public static void testMetricSave(MetricAPI metricAPI){
		
		Metric metric1 = new Metric();
		metric1.setBaseWeight(51);
		metric1.setTimeStamp(25L);
		metric1.setValue(36);
		metricAPI.save(metric1);
		
		Metric metric2 = new Metric();
		metric2.setBaseWeight(61);
		metric2.setTimeStamp(35L);
		metric2.setValue(46);
				
		metricAPI.save(metric2);
	}
	
	public static void testMetricRead(MetricAPI metricAPI){
		
		List<Metric> metricList = metricAPI.read();
		System.out.println("testMetricRead : Size of Metric Collection : "+metricList.size());
	}
	
	public static void testMetricReadByRange(MetricAPI metricAPI){
		List<Metric> metricList = metricAPI.readByTimeRange(24L, 35L);
		System.out.println("testMetricReadByRange : Size of Metric Collection : "+metricList.size());
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
