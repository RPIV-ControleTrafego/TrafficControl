package com.infraction.serviceinfraction.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infraction.serviceinfraction.entity.InfractionEntity;
import com.infraction.serviceinfraction.service.InfractionService;

@Controller
@RequestMapping("/infraction")
@CrossOrigin(origins = "*") 
public class InfractionController {

    

    
    @Autowired
    private InfractionService infractionService;

    @GetMapping("/date/{date}")
    public ResponseEntity<List<InfractionEntity>> getInfractionByDate(@PathVariable("date") String dateString) {
        InfractionCommand command = new InfractionCommand(dateString, infractionService);
        return command.apply(dateString);
    }
        

    @GetMapping("/speed-greater/{speed}")
    public ResponseEntity<List<InfractionEntity>> getInfractionBySpeed(@PathVariable("speed") int speed) {
        InfractionCommand command = new InfractionCommand(String.valueOf(speed), infractionService);
        return command.applySpeedGreaterThan(String.valueOf(speed));
    }



    // public ResponseEntity<List<InfractionEntity>> getInfractionBySpeed(@PathVariable("speed") int speed) {
    //     try {
    //         List<InfractionEntity> infractions = infractionService.getSpeedInfractionGreaterThan(speed);
    
    //         return ResponseEntity.ok().body(infractions);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body(null); // Internal server error
    //     }
    // }

    
    @GetMapping("/speed-lower/{speed}")
    public ResponseEntity<List<InfractionEntity>> getInfractionBySpeedLower(@PathVariable("speed") int speed) {
        InfractionCommand command = new InfractionCommand(String.valueOf(speed), infractionService);
        return command.applySpeedLowerThan(String.valueOf(speed));
    }
    //     try {
    //         List<InfractionEntity> infractions = infractionService.getSpeedInfractionLowerThan(speed);
    
    //         return ResponseEntity.ok().body(infractions);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body(null); // Internal server error
    //     }
    // }   

    @GetMapping("/car-plate/{carPlate}")
    public ResponseEntity<List<Double>> getFinePriceByCarPlate(@PathVariable("carPlate") String carPlate) {
        InfractionCommand command = new InfractionCommand(carPlate, infractionService);
        ResponseEntity<?> responseEntity = command.applyFineByPlate(carPlate);
        List<Double> finePrices = new ArrayList<>();
        if (responseEntity.getBody() != null && responseEntity.getBody() instanceof List<?>) {
            for (Object obj : (List<?>) responseEntity.getBody()) {
                if (obj instanceof Double) {
                    finePrices.add((Double) obj);
                }
            }
        }
        return ResponseEntity.status(responseEntity.getStatusCode()).body(finePrices);
    }

@GetMapping("/cpf/{cpf}")
public ResponseEntity<List<Double>> getFinePriceByCpf(@PathVariable("cpf") String cpf) {
    InfractionCommand command = new InfractionCommand(cpf, infractionService);
    ResponseEntity<?> responseEntity = command.applyFineByPlate(cpf);
    List<Double> finePrices = new ArrayList<>();
    if (responseEntity.getBody() != null && responseEntity.getBody() instanceof List<?>) {
        for (Object obj : (List<?>) responseEntity.getBody()) {
            if (obj instanceof Double) {
                finePrices.add((Double) obj);
            }
        }
    }
    return ResponseEntity.status(responseEntity.getStatusCode()).body(finePrices);

}



@GetMapping("/total-fine-price/real/{cpf}")
public ResponseEntity<List<Double>> getTotalFinePriceByCpf(@PathVariable("cpf") String cpf) {
    InfractionCommand command = new InfractionCommand(cpf, infractionService);
    ResponseEntity<?> responseEntity = command.applyFineByPlate(cpf);
    List<Double> finePrices = new ArrayList<>();
    if (responseEntity.getBody() != null && responseEntity.getBody() instanceof List<?>) {
        for (Object obj : (List<?>) responseEntity.getBody()) {
            if (obj instanceof Double) {
                finePrices.add((Double) obj);
            }
        }
    }
    return ResponseEntity.status(responseEntity.getStatusCode()).body(finePrices);
}

@GetMapping("/total-fine-price/dollar/{cpf}")
public ResponseEntity<String> getTotalFinePriceInDollarsByCpf(@PathVariable("cpf") String cpf) {
    try {
        List<InfractionEntity> infractions = infractionService.getFinePriceByCpf(cpf);
        Double totalFinePrice = infractions.stream()
                .mapToDouble(InfractionEntity::getFinePrice)
                .sum();

        // Chama a função para converter o total de multas para dólares
        Double totalFinePriceInDollars = infractionService.convertCurrency(totalFinePrice, "BRL", "USD");

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedPrice = nf.format(totalFinePriceInDollars);

        return ResponseEntity.ok().body(formattedPrice);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(null); // Internal server error
    }
}

@GetMapping("/total-fine-price/euro/{cpf}")
public ResponseEntity<String> getTotalFinePriceInEurosByCpf(@PathVariable("cpf") String cpf) {
    try {
        List<InfractionEntity> infractions = infractionService.getFinePriceByCpf(cpf);
        Double totalFinePrice = infractions.stream()
                .mapToDouble(InfractionEntity::getFinePrice)
                .sum();

        // Chama a função para converter o total de multas para euros
        Double totalFinePriceInEuros = infractionService.convertCurrency(totalFinePrice, "BRL", "EUR");

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        String formattedPrice = nf.format(totalFinePriceInEuros);

        return ResponseEntity.ok().body(formattedPrice);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(null); // Internal server error
    }
}

//peso argentino
@GetMapping("/total-fine-price/argentine-peso/{cpf}")
public ResponseEntity<String> getTotalFinePriceInArgentinePesoByCpf(@PathVariable("cpf") String cpf) {
    try {
        List<InfractionEntity> infractions = infractionService.getFinePriceByCpf(cpf);
        Double totalFinePrice = infractions.stream()
                .mapToDouble(InfractionEntity::getFinePrice)
                .sum();

        // Chama a função para converter o total de multas para pesos argentinos
        Double totalFinePriceInArgentinePeso = infractionService.convertCurrency(totalFinePrice, "BRL", "ARS");

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
        String formattedPrice = nf.format(totalFinePriceInArgentinePeso);

        return ResponseEntity.ok().body(formattedPrice);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(null); // Internal server error
    }

}

    @GetMapping("/peak-days")
    public ResponseEntity<Map<String, Integer>> findPeakDays() {
        List<InfractionEntity> infractions = infractionService.getAllInfractions(); // Assuming you have a method to retrieve all infractions
        Map<String, Integer> peakDays = infractionService.findPeakDays(infractions);
        return ResponseEntity.ok(peakDays);
    }

    @GetMapping("/find-infraction")
    public ResponseEntity<String> findInfractionByViolation(@RequestParam("violation") String violation) {
        String result = infractionService.findInfractionByViolation(violation);
        return ResponseEntity.ok(result);
    }

  
    @GetMapping("/percentage-infraction-by-sex")
    public ResponseEntity<Float> percentageInfractionBySex(@RequestParam("sex") String sex) {
        float percentage = infractionService.percentageInfractionBySex(sex);
        return ResponseEntity.ok(percentage);
    }

    @GetMapping("/percentage-infraction-by-age")
    public ResponseEntity<Float> percentageInfractionByAge(@RequestParam("age") String age) {
        float percentage = infractionService.percentageInfractionByAge(age);
        return ResponseEntity.ok(percentage);
    }

    @GetMapping("/percentage-infraction-by-car-type")
    public ResponseEntity<Float> percentageInfractionByCarType(@RequestParam("carType") String carType) {
        float percentage = infractionService.percentageInfractionByCarType(carType);
        return ResponseEntity.ok(percentage);
    }

    @GetMapping("/percentage-infraction-by-car-color")
    public ResponseEntity<Float> percentageInfractionByCarColor(@RequestParam("carColor") String carColor) {
        float percentage = infractionService.percentageInfractionByCarColor(carColor);
        return ResponseEntity.ok(percentage);
    }

    @GetMapping("/percentage-infraction-by-car-brand")
    public ResponseEntity<Float> percentageInfractionByCarBrand(@RequestParam("carBrand") String carBrand) {
        float percentage = infractionService.percentageInfractionByCarBrand(carBrand);
        return ResponseEntity.ok(percentage);
    }

}