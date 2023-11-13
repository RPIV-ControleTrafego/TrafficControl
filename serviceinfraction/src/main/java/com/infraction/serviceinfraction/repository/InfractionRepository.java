package com.infraction.serviceinfraction.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


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
    @Query("{ 'violations' : ?0 }")
    List<InfractionEntity> findInfractionByViolations(String violations);
    @Query("{'sex' : ?0}")
    List<InfractionEntity> findViolationBySex(String sex);
    @Query("{'age' : ?0}")
    List<InfractionEntity> findViolationByAge(int age);
}

