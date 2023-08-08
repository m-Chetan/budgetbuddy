package com.budgetbuddy.statisticsservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.budgetbuddy.statisticsservice.domain.timeseries.DataPoint;
import com.budgetbuddy.statisticsservice.domain.timeseries.DataPointId;

@Repository
public interface DataPointRepository extends MongoRepository<DataPoint,DataPointId>{
    List<DataPoint> findByIdAccount(String account);
}
