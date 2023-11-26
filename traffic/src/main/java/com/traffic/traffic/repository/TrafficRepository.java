package com.traffic.traffic.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import com.traffic.traffic.dto.TrafficDto;
import com.traffic.traffic.entity.TrafficEntity;

public interface TrafficRepository extends MongoRepository<TrafficEntity, String>{
    
    List<TrafficEntity> findByCarPlate(String carPlate);
    List<TrafficEntity> findByCarType(String carType);
    @Query("SELECT t FROM TrafficEntity t WHERE t.carPlate IS NOT NULL")
    List<TrafficEntity> findAllCarPlate();
    
    @Query("SELECT t.veiculeOwnerName FROM TrafficEntity t WHERE t.carPlate = :carPlate")
    String findOwnerNameByPlate(@Param("carPlate") String carPlate);

     @Query("SELECT t.veiculeOwneCPF FROM TrafficEntity t WHERE t.carPlate = :carPlate")
    String findOwnerCPFByPlate(@Param("carPlate") String carPlate);


    @Query("SELECT t.carPlate FROM TrafficEntity t WHERE t.veiculeOwneCPF = :ownerCpf")
    String findCarPlateByOwnerName(String ownerName);

    @Query("SELECT t.carPlate FROM TrafficEntity t WHERE t.veiculeOwnerName = :ownerName")
    String findCarPlateByOwnerCPF(String ownerCpf);

    @Query("SELECT t.carPlate FROM TrafficEntity t WHERE t.address = :address")
    String findCarPlateByAdress(String addres);

    @Query("SELECT t.carPlate FROM TrafficEntity t WHERE t.date = :date")
    String findCarPlateByDate(String date);

    @Query("SELECT DISTINCT t.carType FROM TrafficEntity t WHERE t.carType IS NOT NULL")
    List<String> findAllCarTypes();

    @Query("SELECT DISTINCT t.carColor FROM TrafficEntity t WHERE t.carColor IS NOT NULL")
    List<String> findAllCarColors();

    @Query("SELECT DISTINCT t.carBrand FROM TrafficEntity t WHERE t.carBrand IS NOT NULL")
    List<String> findAllCarBrands();

}
