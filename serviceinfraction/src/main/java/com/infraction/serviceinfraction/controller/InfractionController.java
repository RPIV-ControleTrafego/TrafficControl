package com.infraction.serviceinfraction.controller;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.entity.InfractionEntity;
import com.infraction.serviceinfraction.service.InfractionService;

@Controller
@RequestMapping("/infraction")
public class InfractionController {

    

    
    @Autowired
    private InfractionService infractionService;

    @GetMapping("/date/{date}")
    public ResponseEntity<List<InfractionEntity>> getInfractionByDate(@PathVariable("date") String dateString) {
        try {
            List<InfractionEntity> infractions = infractionService.getInfractionsInDate(dateString);
    
            return ResponseEntity.ok().body(infractions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal server error
        }
    }

    @GetMapping("/speed-greater/{speed}")
    public ResponseEntity<List<InfractionEntity>> getInfractionBySpeed(@PathVariable("speed") int speed) {
        try {
            List<InfractionEntity> infractions = infractionService.getSpeedInfractionGreaterThan(speed);
    
            return ResponseEntity.ok().body(infractions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal server error
        }
    }

    @GetMapping("/speed-lower/{speed}")
    public ResponseEntity<List<InfractionEntity>> getInfractionBySpeedLower(@PathVariable("speed") int speed) {
        try {
            List<InfractionEntity> infractions = infractionService.getSpeedInfractionLowerThan(speed);
    
            return ResponseEntity.ok().body(infractions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal server error
        }
    }   

    @GetMapping("/car-plate/{carPlate}")
public ResponseEntity<List<Double>> getFinePriceByCarPlate(@PathVariable("carPlate") String carPlate) {
    try {
        List<Double> finePrices = infractionService.getFinePriceByCarPlate(carPlate)
                .stream()
                .map(InfractionEntity::getFinePrice)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(finePrices);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(null); // Internal server error
    }
}

@GetMapping("/cpf/{cpf}")
public ResponseEntity<List<Double>> getFinePriceByCpf(@PathVariable("cpf") String cpf) {
    try {
        List<Double> finePrices = infractionService.getFinePriceByCpf(cpf)
                .stream()
                .map(InfractionEntity::getFinePrice)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(finePrices);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(null); // Internal server error
    }

}



@GetMapping("/total-fine-price/real/{cpf}")
public ResponseEntity<String> getTotalFinePriceByCpf(@PathVariable("cpf") String cpf) {
    try {
        List<InfractionEntity> infractions = infractionService.getFinePriceByCpf(cpf);
        Double totalFinePrice = infractions.stream()
                .mapToDouble(InfractionEntity::getFinePrice)
                .sum();

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String formattedPrice = nf.format(totalFinePrice);

        return ResponseEntity.ok().body(formattedPrice);
    } catch (Exception e) {
        return ResponseEntity.status(500).body(null); // Internal server error
    }
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

}