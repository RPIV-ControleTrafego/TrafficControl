package com.accident.serviceaccident.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
@Builder
@Component
@Document("accident")
public class AccidentEntity {

  
    private String tipo;
    private int severidade;
    private String data;
    private int vitimas;
    private String hora;
    
}
