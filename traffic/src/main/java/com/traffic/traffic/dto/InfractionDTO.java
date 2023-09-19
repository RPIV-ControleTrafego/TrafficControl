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
public class InfractionDTO {
    
   
    private String carPlate;
    private String address;
    private String date;
    private String violation;
    private String carType;
    private String carColor;
    private String carBrand;
    private String veiculeOwnerName;
    private String veiculeOwneCPF;
    private double speed;
    private double maxSpeed;
    


    

}
