package com.infraction.serviceinfraction.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "infraction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
@Builder
@Component
public class InfractionEntity {

    @Id
    private ObjectId id;
    private String idInfraction;
    private String carPlate;
    private String addres;
    private String date;
    private String violation;
    private String carType;
    private String carColor;
    private String carBrand;
    private String veiculeOwnerName;
    private String veiculeOwneCPF;
    private double speed;
    private double maxSpeed;
    private double finePrice;
    private String sex;
    private String age;
    private boolean isPaid;


}
