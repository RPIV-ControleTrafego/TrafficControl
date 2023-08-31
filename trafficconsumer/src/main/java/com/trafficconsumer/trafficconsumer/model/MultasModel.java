package com.trafficconsumer.trafficconsumer.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Document(collection = "multas")
public class MultasModel {
    
    int pontosCarteira;
    String nomeCondutor;
    @JsonProperty("placaVeiculo")
    String placaVeiculo;
    String data;
    double valor;
    
}
