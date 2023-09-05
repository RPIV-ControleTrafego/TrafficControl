package com.traffic.traffic.entity;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "jogos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
@Builder
public class TrafficEntity{
    

    private String carPlate;
    private String carType;
    private String carColor;
    private String carBrand;
    
}
