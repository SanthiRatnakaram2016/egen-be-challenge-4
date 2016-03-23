package com.sensor.microservices;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


//@EntityScan("io.pivotal.microservices.accounts")
@SpringBootApplication
@ComponentScan("com.sensor.dao , com.sensor.service , com.sensor.controller, com.sensor.rules" )
@ImportResource({"/applicationContext-dao.xml","/applicationContext-rules.xml"})
public class SensorWebApplication {

}
