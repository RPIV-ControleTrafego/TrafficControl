package com.infraction.serviceinfraction.entity;

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

    private String carPlate;
    private String addres;
    private String date;
    private String violation;
    private String carType;
    private String carColor;
    private String carBrand;
    private String veiculeOwnerName;
    private String veiculeOwneCPF;

}
