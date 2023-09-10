package com.traffic.traffic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.traffic.traffic.Service.ITrafficService;
import com.traffic.traffic.dto.TrafficDto;
import com.traffic.traffic.repository.TrafficRepository;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/service/traffic")
public class trafficController {

    @Autowired
    private ITrafficService trafficService;

   
    @GetMapping("/car-plate/{carPlate}")
    public ResponseEntity<TrafficDto> getCarByPlate(@PathVariable String carPlate) {
        try {
            // Verifique se a placa é nula ou vazia e trate-a adequadamente
            if (carPlate == null || carPlate.isEmpty()) {
                // Retorne um status HTTP 400 (Bad Request) para indicar que a entrada é inválida
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Realize a pesquisa no serviço usando o número da placa
            TrafficDto trafficDto = trafficService.getCarByPlate(carPlate);

            // Verifique se o objeto foi encontrado
            if (trafficDto != null) {
                // Retorne um status HTTP 200 (OK) com o objeto encontrado em JSON
                return new ResponseEntity<>(trafficDto, HttpStatus.OK);
            } else {
                // Retorne um status HTTP 404 (Not Found) se o objeto não for encontrado
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Trate a exceção de acordo com os requisitos do seu aplicativo
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    


    @GetMapping("/car-plate/list")
    public ResponseEntity<List<TrafficDto>> listCarsPlate() {
        try {
            // Realize a consulta no serviço para listar todos os carros
            List<TrafficDto> trafficDtos = trafficService.listCarsPlate();

            // Verifique se a lista está vazia
            if (trafficDtos.isEmpty()) {
                // Retorne um status HTTP 404 (Not Found) se a lista estiver vazia
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                // Retorne um status HTTP 200 (OK) com a lista de objetos encontrados em JSON
                return new ResponseEntity<>(trafficDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            // Trate a exceção de acordo com os requisitos do seu aplicativo
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/car-plate/owner/{carPlate}")
    public ResponseEntity<String> getOwnerByPlate(@PathVariable String carPlate) {
        try {
            // Verifique se a placa é nula ou vazia e trate-a adequadamente
            if (carPlate == null || carPlate.isEmpty()) {
                // Retorne um status HTTP 400 (Bad Request) para indicar que a entrada é inválida
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    
            // Realize a pesquisa no serviço usando o número da placa
            TrafficDto trafficDto = trafficService.getCarByPlate(carPlate);
    
            // Verifique se trafficDto é nulo e retorne um status HTTP 404 (Not Found) se for o caso
            if (trafficDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    
            // Supondo que trafficDto.getVeiculeOwnerName() retorna o nome do proprietário do veículo
            String ownerName = trafficDto.getVeiculeOwnerName();
            
            // Retorne o nome do proprietário em um ResponseEntity de sucesso (status HTTP 200 OK)
            return new ResponseEntity<>("Owner Name: "+ownerName, HttpStatus.OK);
        } catch (Exception e) {
            // Trate a exceção de acordo com os requisitos do seu aplicativo
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

  
}

 


  



