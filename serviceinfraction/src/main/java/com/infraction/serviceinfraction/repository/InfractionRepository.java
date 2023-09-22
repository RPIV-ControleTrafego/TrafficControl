package com.infraction.serviceinfraction.repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infraction.serviceinfraction.entity.InfractionEntity;




public interface InfractionRepository extends MongoRepository<InfractionEntity, String>{

    List<InfractionEntity> findByCarPlate(String carPlate);
  
    @Query("{ 'date' : { $gte: ?0 } }")
    List<InfractionEntity> findByDateGreaterThanEqual(String date);
}

