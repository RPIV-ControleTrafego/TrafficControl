package com.infraction.serviceinfraction.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infraction.serviceinfraction.entity.InfractionEntity;




public interface InfractionRepository extends MongoRepository<InfractionEntity, String>{

    List<InfractionEntity> findByCarPlate(String carPlate);
    
}
