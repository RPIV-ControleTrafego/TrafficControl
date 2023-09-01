package com.api.api.dto;



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
    
    private String infractionId;
    private String carPlate;
    private String addres;
    private String date;
    private String type;
    private String description;
    private String status;

}
