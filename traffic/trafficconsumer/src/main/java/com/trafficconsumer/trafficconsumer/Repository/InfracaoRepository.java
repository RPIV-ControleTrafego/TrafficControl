package com.trafficconsumer.trafficconsumer.Repository;

import com.trafficconsumer.trafficconsumer.model.InfracaoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InfracaoRepository extends MongoRepository<InfracaoModel, String> {
    List<InfracaoModel> findByVelocidadeVeiculo(int velocidadeVeiculo);
    List<InfracaoModel> findByData(String data);
    List<InfracaoModel> findByTipoVeiculo(String tipoVeiculo);
}
