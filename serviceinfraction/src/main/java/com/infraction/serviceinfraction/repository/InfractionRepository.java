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
    @Query("{ 'speed' : { $gte: ?0 } }'}")
    List<InfractionEntity> findSpeedInfractioGreaterThan(int speed);
    @Query("{ 'speed' : { $lte: ?0 } }'}")
    List<InfractionEntity> findSpeedInfractionLowerThan(int speed);
    @Query("{ 'carPlate' : ?0 }")
    List<InfractionEntity> findInfractionByCarPlate(String carPlate);
    @Query("{ 'veiculeOwneCPF' : ?0 }")
    List<InfractionEntity> findInfractionByVeiculeOwnerCpf(String veiculeOwnerCpf);

}

