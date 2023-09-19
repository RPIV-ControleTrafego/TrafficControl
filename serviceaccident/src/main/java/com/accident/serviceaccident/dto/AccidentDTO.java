package com.accident.serviceaccident.dto;

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
public class AccidentDTO{
    
    private String severity;
    private String date;
    private boolean hasInjuries;
    private boolean hasFatalities;
    private String address;
    private String description;
    private String type;
    private boolean hasInfraction;

}
