package com.sensor.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sensor.domain.Metric;

@Service
public class WebSensorService {

	@Autowired        // Created automatically by Spring Cloud
    @LoadBalanced
    protected RestTemplate restTemplate; 

    public WebSensorService() {
		
	}

	protected String serviceUrl;

    public WebSensorService(String serviceUrl) {
    	serviceUrl = "http://SENSOR-SERVICE";
        this.serviceUrl = serviceUrl.startsWith("http") ?
        		"http://SENSOR-SERVICE" : "http://" + serviceUrl;
    }

    public void readMetrics(String accountNumber) {
    	ResponseEntity<Metric[]> responseEntity = restTemplate.getForEntity(serviceUrl+ "/sensor/metrics", Metric[].class);
    	Metric[] metrics= responseEntity.getBody();
    	//MediaType contentType = responseEntity.getHeaders().getContentType();
    	//HttpStatus statusCode = responseEntity.getStatusCode();
    	System.out.println(metrics);
    	
    }
}
