package com.traffic.traffic.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traffic.traffic.dto.TrafficDto;
import com.traffic.traffic.entity.TrafficEntity;
import com.traffic.traffic.repository.TrafficRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TrafficService implements ITrafficService {

    private final Logger log = LoggerFactory.getLogger(TrafficService.class);

    @Autowired
    private TrafficRepository trafficRepository;

    public void newCarDetails(TrafficDto trafficDto) {
        TrafficEntity trafficEntity = mapCarDtoToEntity(trafficDto);
        
      
        
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
    public void changeCarPlate(TrafficDto TrafficDto, String carPlate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeCarPlate'");
    }

    @Override
    public void removeCar(String carPlate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeCar'");
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

                
                
                
                .build();

                

    }



    @Override
    public List<TrafficDto> getVeiculeOwnerName(String carPlate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVeiculeOwnerName'");
    }



    @Override
    public List<TrafficDto> getVeiculeOwnerCPF(String carPlate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVeiculeOwnerCPF'");
    }



    @Override
    public List<TrafficDto> getCarPlateByCPF(String ownerCpf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarPlateByCPF'");
    }



    @Override
    public List<TrafficDto> getCarPlateByOwnerName(String ownerName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarPlateByOwnerName'");
    }



    @Override
    public List<TrafficDto> getCarPlateByAdress(String addres) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarPlateByAdress'");
    }



    @Override
    public List<TrafficDto> getCarPlateByDate(String date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarPlateByDate'");
    }



    @Override
    public List<TrafficDto> getCarPlateByTime(String time) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarPlateByTime'");
    }



    @Override
    public List<TrafficDto> getCarPlateBySpeed(double speed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarPlateBySpeed'");
    }



   


    


    @Override
    public void getCarTypes(TrafficDto trafficDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarTypes'");
    }



    @Override
    public void getCarColors(TrafficDto trafficDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarColors'");
    }



    @Override
    public void getCarBrands(TrafficDto trafficDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarBrands'");
    }



    
    
}

