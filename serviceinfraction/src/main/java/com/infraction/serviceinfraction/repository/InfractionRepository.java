package com.infraction.serviceinfraction.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infraction.serviceinfraction.entity.InfractionEntity;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;




public interface InfractionRepository extends MongoRepository<InfractionEntity, ObjectId>{

    List<InfractionEntity> findByCarPlate(String carPlate);
  
    @Query("{ 'date' : { $gte: ?0 } }")
    List<InfractionEntity> findByDateGreaterThanEqual(String date);
    @Query("{ 'speed' : { $gte: ?0 } }'}")
    List<InfractionEntity> findSpeedInfractioGreaterThan(int speed);
    @Query("{ 'speed' : { $lte: ?0 } }'}")
    List<InfractionEntity> findSpeedInfractionLowerThan(int speed);
    @Query("{ 'carPlate' : ?0 }")
    List<InfractionEntity> findInfractionByCarPlate(String carPlate);
    @Query("{'isPaid': false, 'veiculeOwneCPF' : ?0 }")
    List<InfractionEntity> findInfractionByVeiculeOwnerCpf(String veiculeOwnerCpf);
    @Query("{ 'violations' : ?0 }")
    List<InfractionEntity> findInfractionByViolations(String violations);
    @Query("{'sex' : ?0}")
    List<InfractionEntity> findViolationBySex(String sex);
    @Query("{'age' : ?0}")
    List<InfractionEntity> findViolationByAge(int age);
    @Query("{ 'veiculeOwnerCpf' : ?0 }")
    List<InfractionEntity> findByVeiculeOwnerCpf(String veiculeOwnerCpf, Sort sort);
    @Query("{'date' : ?0}")
    List<InfractionEntity> findByDate(String date);

    @Query("{'isPaid': false, 'veiculeOwneCPF': ?0}")
    List<InfractionEntity> findByIsPaidFalseAndCpf(String cpf);


    @Query("{'isPaid': false, 'veiculeOwneCPF': ?0, 'date': ?1}")
    List<InfractionEntity> findByIsPaidFalseAndCpfAndDate(String cpf, String date);

    
    Optional<InfractionEntity> findById(ObjectId id);

    // Se desejar um método que retorne diretamente InfractionEntity em vez de Optional
    InfractionEntity findInfractionEntityById(ObjectId id);


    @Query("{'isPaid': false}")
    List<InfractionEntity> findByIsPaidFalse();

    //find paid infractions
    @Query("{'isPaid': true}")
    List<InfractionEntity> findByIsPaidTrue();

    //find paid infractions by cpf
    @Query("{'isPaid': true, 'veiculeOwneCPF': ?0}")
    List<InfractionEntity> findByIsPaidTrueAndCpf(String cpf);
    Optional<InfractionEntity> findByIdInfraction(String idInfraction);



}

