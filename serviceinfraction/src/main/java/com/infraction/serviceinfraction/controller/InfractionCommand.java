package com.infraction.serviceinfraction.controller;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.infraction.serviceinfraction.entity.InfractionEntity;
import com.infraction.serviceinfraction.service.InfractionService;
public class InfractionCommand implements Function<String, ResponseEntity<List<InfractionEntity>>> {
    private final String parameter;
    private final InfractionService infractionService;
    public InfractionCommand(String parameter, InfractionService infractionService) {
        this.parameter = parameter;
        this.infractionService = infractionService;
    }
    @Override
    public ResponseEntity<List<InfractionEntity>> apply(String parameter) {
        List<InfractionEntity> infractions = infractionService.getInfractionsInDate(parameter);
        return ResponseEntity.ok(infractions);
    }
    public ResponseEntity<List<InfractionEntity>> applySpeedGreaterThan(String parameter) {
        List<InfractionEntity> infractions = infractionService.getSpeedInfractionGreaterThan(Integer.parseInt(parameter));
        return ResponseEntity.ok(infractions);
    }
    public ResponseEntity<List<InfractionEntity>> applySpeedLowerThan(String parameter) {
        List<InfractionEntity> infractions = infractionService.getSpeedInfractionLowerThan(Integer.parseInt(parameter));
        return ResponseEntity.ok(infractions);
    }
    public ResponseEntity<List<InfractionEntity>> applyCarPlate(String parameter) {
        List<InfractionEntity> infractions = infractionService.getInfractionByVeiculeOwnerCpf(parameter);
        return ResponseEntity.ok(infractions);
    }
    public ResponseEntity<List<InfractionEntity>> applyVeiculeOwnerCpf(String parameter) {
        List<InfractionEntity> infractions = infractionService.getInfractionByVeiculeOwnerCpf(parameter);
        return ResponseEntity.ok(infractions);
    }
    public ResponseEntity<List<InfractionEntity>> applyViolations(String parameter) {
        List<InfractionEntity> infractions = infractionService.getInfractionsInDate(parameter);
        return ResponseEntity.ok(infractions);
    }
    public ResponseEntity<List<InfractionEntity>> applyFine(String parameter) {
        List<InfractionEntity> infractions = infractionService.getInfractionsInDate(parameter);
        return ResponseEntity.ok(infractions);
    }
    public ResponseEntity<?> applyFineByPlate(String parameter) {
        try {
            List<Double> finePrices = infractionService.getFinePriceByCarPlate(parameter)
                    .stream()
                    .map(InfractionEntity::getFinePrice)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(finePrices);
        } catch (Exception e) {
            // Em caso de erro, retorne um status HTTP 500 com uma mensagem de erro
            String errorMessage = "Erro ao obter os preços das multas.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    public ResponseEntity<?> applyFineByCpf(String parameter) {
        try {
            List<Double> finePrices = infractionService.getFinePriceByCpf(parameter)
                    .stream()
                    .map(InfractionEntity::getFinePrice)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(finePrices);
        } catch (Exception e) {
            // Em caso de erro, retorne um status HTTP 500 com uma mensagem de erro
            String errorMessage = "Erro ao obter os preços das multas.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    public ResponseEntity<?> applyTotalFinePriceByCpf(String parameter) {
        try {
            List<InfractionEntity> infractions = infractionService.getFinePriceByCpf(parameter);
            Double totalFinePrice = infractions.stream()
                    .mapToDouble(InfractionEntity::getFinePrice)
                    .sum();
            return ResponseEntity.ok(totalFinePrice);
        } catch (Exception e) {
            // Em caso de erro, retorne um status HTTP 500 com uma mensagem de erro
            String errorMessage = "Erro ao obter o preço total das multas.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
