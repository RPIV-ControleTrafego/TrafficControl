package com.traffic.traffic.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrafficDto {
    
    private String carPlate;
    private String carType;
    private String carColor;
    private String carBrand;
    
}
