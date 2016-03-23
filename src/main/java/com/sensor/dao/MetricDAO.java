package com.sensor.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.domain.Metric;

@Repository("metricDAO")
public class MetricDAO extends BasicDAO<Metric, String> implements SensorDAO {

	@Autowired
    protected MetricDAO(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();
    }
	
	public List<Metric> read() {
		return this.getDatastore().find(Metric.class).asList();
	}
	
	public List<Metric> readByTimeRange(Long startTime, Long endTime) {
		
		Query<Metric> query = this.getDatastore().find(Metric.class);
		query.and(query.criteria("timeStamp").greaterThan(new Long(startTime)), query.criteria("timeStamp").lessThan(new Long(endTime)));
		
		return query.asList();
	}
	
}
