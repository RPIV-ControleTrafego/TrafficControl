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
public class AccidentDTO{
    
   private String tipo;
    private int severidade;
    private String data;
    private int vitimas;
    private String hora;

}
