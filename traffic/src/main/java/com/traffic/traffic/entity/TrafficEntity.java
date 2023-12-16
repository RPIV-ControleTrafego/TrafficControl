package com.traffic.traffic.entity;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
// import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "trafficData")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
@Builder
public class TrafficEntity{
    
    @Id
    @Field("idTraffic")
    private String idTraffic;
    private String carPlate;
    private String carType;
    private String carColor;
    private String carBrand;
    private String veiculeOwnerName;
    private String veiculeOwneCPF;
    private String time;
    private String date;
    private String address;
    private double speed;
    private int maxSpeed;
    private String direction; // north, south, east, west
    private String streetDirection; // north, south, east, west
    private String violation;
    private double pollutionLevel;
    private int age;
    private String sex;
    
    
}
