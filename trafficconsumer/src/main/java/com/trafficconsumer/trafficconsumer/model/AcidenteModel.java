package com.trafficconsumer.trafficconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcidenteModel {
    
    int numEnvolvidos;

    String gravidade;
    
    String data;
    String hora;
    String endereco;



}
