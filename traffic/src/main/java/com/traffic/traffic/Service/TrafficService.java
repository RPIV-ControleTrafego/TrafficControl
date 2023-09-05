package com.traffic.traffic.Service;

import java.util.List;

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
    public List<TrafficDto> getCarPlates() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarPlates'");
    }

    @Override
    public List<TrafficDto> getCarTypes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarTypes'");
    }

    @Override
    public List<TrafficDto> getCarColors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarColors'");
    }

    @Override
    public List<TrafficDto> getCarBrands() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarBrands'");
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

       

        return trafficEntity;
    }

    private TrafficDto mapCarEntityToDTO(TrafficEntity trafficEntity){

        return TrafficDto.builder()
                .carBrand(trafficEntity.getCarBrand())
                .carType(trafficEntity.getCarType())
                .carColor(trafficEntity.getCarColor())
                .carPlate(trafficEntity.getCarPlate()).build();

    }

    
}


// package com.store.car.service;

// import com.store.car.dto.CarPostDTO;
// import com.store.car.entity.CarPostEntity;
// import com.store.car.repository.CarPostRepository;
// import com.store.car.repository.OwnerPostRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.NoSuchElementException;

// @Service
// public class CarPostServiceImpl implements  CarPostService {

//     @Autowired
//     private CarPostRepository carPostRepository;

//     @Autowired
//     private OwnerPostRepository ownerPostRepository;

//     @Override
//     public void newPostDetails(CarPostDTO carPostDTO) {
//         CarPostEntity carPostEntity = mapCarDtoToEntity(carPostDTO);
//         carPostRepository.save(carPostEntity);
//     }

//     @Override
//     public List<CarPostDTO> getCarSales() {
//         List<CarPostDTO> listCarsSales = new ArrayList<>();
//         carPostRepository.findAll().forEach(item->{
//             listCarsSales.add(mapCarEntityToDTO(item));
//         });
//         return listCarsSales;
//     }

//     @Override
//     public void changeCarSale(CarPostDTO carPostDTO, Long postId) {

//         carPostRepository.findById(postId).ifPresentOrElse(item->{
//             item.setDescription(carPostDTO.getDescription());
//             item.setContact(carPostDTO.getContact());
//             item.setPrice(carPostDTO.getPrice());
//             item.setBrand(carPostDTO.getBrand());
//             item.setEngineVersion(carPostDTO.getEngineVersion());
//             item.setModel(carPostDTO.getModel());

//             carPostRepository.save(item);

//         }, ()-> {
//             throw new NoSuchElementException();
//         });
//     }

//     @Override
//     public void removeCarSale(Long postId) {
//         carPostRepository.deleteById(postId);
//     }

//     private CarPostEntity mapCarDtoToEntity(CarPostDTO carPostDTO) {
//         CarPostEntity carPostEntity = new CarPostEntity();

//         ownerPostRepository.findById(carPostDTO.getOwnerId()).ifPresentOrElse(item->{
//             carPostEntity.setOwnerPost(item);
//             carPostEntity.setContact(item.getContactNumber());
//         }, ()-> {
//             throw new RuntimeException();
//         });

//         carPostEntity.setModel(carPostDTO.getModel());
//         carPostEntity.setBrand(carPostDTO.getBrand());
//         carPostEntity.setPrice(carPostDTO.getPrice());
//         carPostEntity.setCity(carPostDTO.getCity());
//         carPostEntity.setDescription(carPostDTO.getDescription());
//         carPostEntity.setEngineVersion(carPostDTO.getEngineVersion());
//         carPostEntity.setCreatedDate(String.valueOf(new Date()));

//         return carPostEntity;
//     }

//     private CarPostDTO mapCarEntityToDTO(CarPostEntity carPostEntity){

//         return CarPostDTO.builder()
//                 .brand(carPostEntity.getBrand())
//                 .city(carPostEntity.getCity())
//                 .model(carPostEntity.getModel())
//                 .description(carPostEntity.getDescription())
//                 .engineVersion(carPostEntity.getEngineVersion())
//                 .createdDate(carPostEntity.getCreatedDate())
//                 .ownerName(carPostEntity.getOwnerPost().getName())
//                 .price(carPostEntity.getPrice()).build();

//     }

// }
