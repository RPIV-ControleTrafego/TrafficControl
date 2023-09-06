package com.traffic.traffic;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.traffic.traffic.Service.TrafficService;
import com.traffic.traffic.dto.TrafficDto;
import com.traffic.traffic.entity.TrafficEntity;
import com.traffic.traffic.repository.TrafficRepository;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class TrafficServiceTest {

    @InjectMocks
    private TrafficService trafficService;

    @Mock
    private TrafficRepository trafficRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCarPlates() {
        // Crie algumas entidades de tráfego simuladas para o teste
        TrafficEntity entity1 = TrafficEntity.builder()
                .carPlate("ABC123")
                .carType("Sedan")
                .carColor("Blue")
                .carBrand("Toyota")
                .build();

        TrafficEntity entity2 = TrafficEntity.builder()
                .carPlate("XYZ789")
                .carType("SUV")
                .carColor("Red")
                .carBrand("Honda")
                .build();

        List<TrafficEntity> trafficEntities = new ArrayList<>();
        trafficEntities.add(entity1);
        trafficEntities.add(entity2);

        // Defina o comportamento simulado para o repositório quando o método findAll for chamado
        when(trafficRepository.findAll()).thenReturn(trafficEntities);

        // Chame o método que você deseja testar
        List<TrafficDto> result = trafficService.getCarPlates();

        // Verifique se o resultado é o esperado
        assertEquals(2, result.size()); // Verifique se a lista de DTOs contém 2 elementos (por exemplo)
        assertEquals("ABC123", result.get(0).getCarPlate()); // Verifique se o primeiro DTO tem a placa de carro esperada
        assertEquals("XYZ789", result.get(1).getCarPlate()); // Verifique se o segundo DTO tem a placa de carro esperada

       
    }
}
