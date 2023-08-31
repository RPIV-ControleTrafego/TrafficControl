package com.trafficconsumer.trafficconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrafficMessage {
   
    int velocidadeVeiculo;
    int velocidadeVia;
    String placaVeiculo;
    String data;
    String tipoVeiculo;


}
