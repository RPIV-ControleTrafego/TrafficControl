package com.trafficconsumer.trafficconsumer.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "infracoes")
public class InfracaoModel {
    
    private int velocidadeVeiculo;
    private int velocidadeVia;
    private String tipoVeiculo;
    private String data;

}
