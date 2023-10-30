package com.traffic.traffic.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.In;
import org.springframework.stereotype.Service;

import com.traffic.traffic.dto.AccidentDTO;
import com.traffic.traffic.dto.AllTraficDTO;
import com.traffic.traffic.dto.InfractionDTO;
import com.traffic.traffic.dto.TrafficDto;
import com.traffic.traffic.dto.Mapper.TrafficMapper;
import com.traffic.traffic.entity.TrafficEntity;
import com.traffic.traffic.message.KafkaConsumerMessage;
import com.traffic.traffic.message.KafkaProducerMessage;
import com.traffic.traffic.repository.TrafficRepository;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TrafficService implements ITrafficService {

    private final Logger log = LoggerFactory.getLogger(TrafficService.class);

    @Autowired
    private TrafficRepository trafficRepository;
    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;
    
  

    public void newCarDetails(AllTraficDTO trafficInfo) {
        TrafficDto trafficDto = trafficMapper.mapCarEntityToDTO(trafficMapper.mapCarDtoToEntity(trafficInfo.getTrafficInfo()));
        InfractionDTO infractionDTO = trafficMapper.mapCarDtoToInfractionDTO(trafficDto);
        AccidentDTO accidentDTO = trafficMapper.mapAllTrafficToAccident(trafficInfo);
        kafkaProducerMessage.sendAccidentMessage(accidentDTO);
        
        verifyInfractionDirection(trafficDto, infractionDTO);
        verifyInfractionspeed(trafficDto, infractionDTO);
        verifyViolations(trafficDto, infractionDTO);
        verifyPlateEmpty(trafficDto, infractionDTO);
        
        TrafficEntity trafficEntity = trafficMapper.mapCarDtoToEntity(trafficDto);
        
        try {
            // Salvar a entidade no MongoDB
            trafficRepository.save(trafficEntity);
            log.info("Entidade salva com sucesso no MongoDB: " + trafficEntity);
        } catch (Exception e) {
            log.error("Erro ao salvar a entidade no MongoDB: " + e.getMessage());
            // Trate a exceção de acordo com os requisitos do seu aplicativo
        }
    }

    @Override
    public TrafficDto getCarByPlate(String carPlate) {
    try {
       

        trafficRepository.findByCarPlate(carPlate);
        log.info("Pesquisado carro utilizando placa " + carPlate);
        return mapCarEntityToDTO(trafficRepository.findByCarPlate(carPlate).get(0));
    } catch (Exception e) {
        log.error("Placa não encontrada", e.getMessage());
    }
    return null;
    }

    @Override
   public List<TrafficDto> listCarsPlate() {
    try {
      
        List<TrafficEntity> carEntities = trafficRepository.findAllCarPlate();
        log.info("Listando carros");

      
        List<TrafficDto> carDtos = new ArrayList<>();

     
        for (TrafficEntity carEntity : carEntities) {
            TrafficDto carDto = mapCarEntityToDTO(carEntity);
            carDtos.add(carDto);
        }

      
        return carDtos;
    } catch (Exception e) {
        log.error("Erro ao listar carros: " + e.getMessage());
        throw new RuntimeException("Erro ao listar carros", e);
    }
}



    @Override
    public void changeCarPlate(TrafficDto TrafficDto, String carPlate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeCarPlate'");
    }

    @Override
    public void removeCar(String carPlate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeCar'");
    }


   


    @Override
public String getOwnerNameByPlate(String carPlate) {
    try {
     
        String ownerName = trafficRepository.findOwnerNameByPlate(carPlate);

      
        if (ownerName != null) {
            log.info("Pesquisado Nome através da placa" + carPlate);
            return ownerName;
        } else {
            log.error("Placa não encontrada");
            return null; 
        }
    } catch (Exception e) {
      
        return null;
    }
}
    


    @Override
    public String getVehicleOwnerCPF(String carPlate) {
       try {
     
        String ownerCPF = trafficRepository.findOwnerCPFByPlate(carPlate);

      
   
           log.info("Pesquisado Nome através da placa" + carPlate);
            return ownerCPF;
       
    } catch (Exception e) {
         log.error("CPF não encontrado em busca de placa por cpf"+ carPlate);
         return "Nome não encontrado, verifique a placa";
    }
    }



    @Override
    public String getCarPlateByCPF(String ownerCpf) {
        
        try{
            

            String carPlate = trafficRepository.findCarPlateByOwnerCPF(ownerCpf);
        
                log.info("Pesquisado placa através do CPF" + ownerCpf);
                return carPlate;
       
              
       

        }catch(Exception e){
                  log.error("CPF não encontrado em busca de placa por cpf"+ ownerCpf);
            return  "Placa não encontrada, verifique o CPF";
        }
    }



    @Override
    public String getCarPlateByOwnerName(String ownerName) {
           try{
            

            String carPlate = trafficRepository.findCarPlateByOwnerName(ownerName);
        
                log.info("Pesquisado placa através do Nome" + ownerName);
                return carPlate;
       
              
       

        }catch(Exception e){
                  log.error("Nome não encontrado em pesquisa de placa por nome:  " + ownerName);
            return  "CPF não encontrado, verifique o nome";
        }
    }



    @Override
    public String getCarPlateByAdress(String addres) {
           try{
            

            String carPlate = trafficRepository.findCarPlateByAdress(addres);
        
                log.info("Pesquisado placa através de Endereço" + addres);
                return carPlate;
       
              
       

        }catch(Exception e){
                  log.error("Placa não encontrada em pesquisa de placa usando endereço:  " + addres);
            return  "Placa não encontrada, verifique o endereço";
        }
    }
    



    @Override
    public String getCarPlateByDate(String date) {
          try{
            

            String carPlate = trafficRepository.findCarPlateByDate(date);
        
                log.info("Pesquisado placa através de data" + date);
                return carPlate;
       
              
       

        }catch(Exception e){
                  log.error("Placa não encontrada em pesquisa de placa usando data:  " + date);
            return  "Placa não encontrada, verifique a data";
        }
    }



    @Override
    public List<TrafficDto> getCarPlateByTime(String time) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarPlateByTime'");
    }



    @Override
public List<String> getCarPlateBySpeed(double speedMax, double speedMin) {
    if (speedMax < speedMin) {
        log.error("Velocidade máxima menor que a mínima");
        return Collections.emptyList();
    }

    try {
        // Encontra carros entre a velocidade máxima e mínima
        List<String> carPlate = trafficRepository.findAll().stream()
                .filter(trafficEntity -> trafficEntity.getSpeed() >= speedMin && trafficEntity.getSpeed() <= speedMax)
                .map(TrafficEntity::getCarPlate)
                .collect(Collectors.toList());

        log.info("Pesquisado placa através de velocidade" + speedMax + speedMin);
        return carPlate;

    } catch (Exception e) {
        log.error("Ocorreu um erro ao pesquisar placas usando velocidade: " + speedMax + speedMin, e);
        return Collections.emptyList();
    }
}


   


    


@Override
public List<String> getCarTypes() {
    try {
        List<String> carTypes = trafficRepository.findAllCarTypes();
        log.info("Pesquisado tipos de carro: " + carTypes);

        // Remove duplicatas da lista
        Set<String> uniqueCarTypes = new HashSet<>(carTypes);

        return new ArrayList<>(uniqueCarTypes);
    } catch (Exception e) {
        log.error("Tipos de carro não encontrados", e.getMessage());
        return Collections.emptyList();
    }
}


    @Override
    public List<String> getCarColors() {
        try {

            List<String> carColors = trafficRepository.findAllCarColors();
           
            log.info("Pesquisado cores de carro" + carColors);
            return carColors;

            
        } catch (Exception e) {
            log.error("Ocorreu um erro ao pesquisar cores de carro", e);
            return Collections.emptyList();
        }
    }



    @Override
    public List<String> getCarBrands() {
            try {

            List<String> carBrands = trafficRepository.findAllCarBrands();
           
            log.info("Pesquisado cores de carro" + carBrands);
            return carBrands;

            
        } catch (Exception e) {
            log.error("Ocorreu um erro ao pesquisar marcas de carro", e);
            return Collections.emptyList();
        }
    }



   

    public boolean verifyInfractionspeed(TrafficDto trafficDto, InfractionDTO infractionDto) {

        if (trafficDto.getSpeed() > trafficDto.getMaxSpeed()) {
            mapCarDtoToInfractionDTO(trafficDto, infractionDto);
            infractionDto.setViolation("speeding");
            kafkaProducerMessage.sendMessage(infractionDto);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean verifyInfractionDirection(TrafficDto trafficDto, InfractionDTO infractionDto) {
        if (trafficDto.getDirection() != trafficDto.getStreetDirection()) {
             mapCarDtoToInfractionDTO(trafficDto, infractionDto);
            infractionDto.setViolation("wrong direction");
            kafkaProducerMessage.sendMessage(infractionDto);
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyViolations(TrafficDto trafficDto,InfractionDTO infractionDto){
        if(trafficDto.getViolation() != null){


            mapCarDtoToInfractionDTO(trafficDto, infractionDto);

            
            if(trafficDto.getDirection() != trafficDto.getStreetDirection()){
                infractionDto.setViolation("wrong direction");
            }
          
            kafkaProducerMessage.sendMessage(infractionDto);
            return true;
    } 
    else {
        return false;
    }

    
    }


    public boolean verifyPlateEmpty(TrafficDto trafficDto,InfractionDTO infractionDto){
        if(trafficDto.getCarPlate() == null){
              mapCarDtoToInfractionDTO(trafficDto, infractionDto);
              infractionDto.setViolation("no plate");
            kafkaProducerMessage.sendMessage(infractionDto);
            return true;
        }
        else{
            return false;
        }

    }



     private TrafficEntity mapCarDtoToEntity(TrafficDto trafficDto) {
        TrafficEntity trafficEntity = new TrafficEntity();

       

        trafficEntity.setCarBrand(trafficDto.getCarBrand());
        trafficEntity.setCarColor(trafficDto.getCarColor());
        trafficEntity.setCarPlate(trafficDto.getCarPlate());
        trafficEntity.setCarType(trafficDto.getCarType());
        trafficEntity.setAddress(trafficDto.getAddress());
        trafficEntity.setDate(trafficDto.getDate());
        trafficEntity.setDirection(trafficDto.getDirection());
        trafficEntity.setMaxSpeed(trafficDto.getMaxSpeed());
        trafficEntity.setSpeed(trafficDto.getSpeed());
        trafficEntity.setStreetDirection(trafficDto.getStreetDirection());
        trafficEntity.setTime(trafficDto.getTime());
        trafficEntity.setVeiculeOwneCPF(trafficDto.getVeiculeOwneCPF());
        trafficEntity.setVeiculeOwnerName(trafficDto.getVeiculeOwnerName());
        trafficEntity.setViolation(trafficDto.getViolation());
        trafficEntity.setPollutionLevel(trafficDto.getPollutionLevel());
       

        return trafficEntity;
    }

    private TrafficDto mapCarEntityToDTO(TrafficEntity trafficEntity){

        return TrafficDto.builder()
                .carBrand(trafficEntity.getCarBrand())
                .carType(trafficEntity.getCarType())
                .carColor(trafficEntity.getCarColor())
                .carPlate(trafficEntity.getCarPlate())
                .address(trafficEntity.getAddress())
                .date(trafficEntity.getDate())
                .direction(trafficEntity.getDirection())
                .maxSpeed(trafficEntity.getMaxSpeed())
                .speed(trafficEntity.getSpeed())
                .streetDirection(trafficEntity.getStreetDirection())
                .time(trafficEntity.getTime())
                .veiculeOwneCPF(trafficEntity.getVeiculeOwneCPF())
                .veiculeOwnerName(trafficEntity.getVeiculeOwnerName())
                .violation(trafficEntity.getViolation())
                .pollutionLevel(trafficEntity.getPollutionLevel())
                
                
                
                .build();

                

    }

    private InfractionDTO mapCarDtoToInfractionDTO(TrafficDto trafficDto, InfractionDTO infractionDto) {
        
        infractionDto.setCarBrand(trafficDto.getCarBrand());
        infractionDto.setCarColor(trafficDto.getCarColor());
        infractionDto.setCarPlate(trafficDto.getCarPlate());
        infractionDto.setCarType(trafficDto.getCarType());
        infractionDto.setAddress(trafficDto.getAddress());
        infractionDto.setDate(trafficDto.getDate());
        // infractionDto.setDirection(trafficDto.getDirection());
        infractionDto.setMaxSpeed(trafficDto.getMaxSpeed());
        infractionDto.setSpeed(trafficDto.getSpeed());
        // infractionDto.setStreetDirection(trafficDto.getStreetDirection());
        // infractionDto.setTime(trafficDto.getTime());
        infractionDto.setVeiculeOwneCPF(trafficDto.getVeiculeOwneCPF());
        infractionDto.setVeiculeOwnerName(trafficDto.getVeiculeOwnerName());
        if(trafficDto.getViolation() != null ){
            infractionDto.setViolation(trafficDto.getViolation());
        }
       



        return infractionDto;
    }

     private TrafficMapper trafficMapper = TrafficMapper.INSTANCE;

    public AllTraficDTO createAllTraficDTO(TrafficDto trafficDto, InfractionDTO infractionDto, AccidentDTO accidentDto) {
        return trafficMapper.mapToAllTraficDTO(trafficDto, infractionDto, accidentDto);
    }   

    
    
   
    
}

