package com.traffic.traffic.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;

import com.traffic.traffic.dto.TrafficDto;
import com.traffic.traffic.entity.TrafficEntity;



public interface TrafficRepository extends MongoRepository<TrafficEntity, String>{
    
    List<TrafficEntity> findByCarPlate(String carPlate);

}
