package com.sensor.domain;
import org.mongodb.morphia.annotations.Entity;

@Entity
public class Metric extends SensorDomain {

	@Override
    public String toString() {
        return String.format(
                "Metric[id=%s, value='%s', baseWeight='%s', timeStamp='%s']",
                getId(), getValue(), getBaseWeight(),getTimeStamp());
    }
	
}
