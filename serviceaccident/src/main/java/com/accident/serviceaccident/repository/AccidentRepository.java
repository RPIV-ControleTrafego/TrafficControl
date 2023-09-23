package com.accident.serviceaccident.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.accident.serviceaccident.Entity.AccidentEntity;

public interface AccidentRepository extends MongoRepository<AccidentEntity, String> {


    
    
}