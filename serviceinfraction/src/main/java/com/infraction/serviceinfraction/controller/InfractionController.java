package com.infraction.serviceinfraction.controller;

import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infraction.serviceinfraction.entity.InfractionEntity;
import com.infraction.serviceinfraction.logger.LoggerInfraction;
import com.infraction.serviceinfraction.service.InfractionService;

@Controller
@RequestMapping("/infraction")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InfractionController {


    private LoggerInfraction log = new LoggerInfraction(InfractionController.class);

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


private Double calculateTotalFinePrice(String cpf) {
    List<InfractionEntity> infractions = infractionService.getFinePriceByCpf(cpf);

    // Log para verificar as infrações recuperadas
    log.info("Infractions for CPF {}: {}", cpf, infractions);

    Double totalFinePrice = infractions.stream()
        .mapToDouble(InfractionEntity::getFinePrice)
        .sum();

    // Log para verificar o total do preço da multa calculado
    log.info("Total Fine Price for CPF {}: {}", cpf, totalFinePrice);

    return totalFinePrice;
}


// @GetMapping("/total-fine-price/{currency}/{cpf}")
// public ResponseEntity<String> getTotalFinePriceByCurrency(@PathVariable("currency") String currency, @PathVariable("cpf") String cpf) {
//     try {
//         Double totalFinePrice = calculateTotalFinePrice(cpf);
//         Double convertedPrice = 0.0;
//         String formattedPrice = "";

//         if (currency.equalsIgnoreCase("dollar")) {
//             convertedPrice = infractionService.convertCurrency(totalFinePrice, "BRL", "USD");
//             NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
//             formattedPrice = nf.format(convertedPrice);
//         } else if (currency.equalsIgnoreCase("euro")) {
//             convertedPrice = infractionService.convertCurrency(totalFinePrice, "BRL", "EUR");
//             NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
//             formattedPrice = nf.format(convertedPrice);
//         } else if(currency.equalsIgnoreCase("real")) {
//             NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
//             formattedPrice = nf.format(totalFinePrice);
//         } else {
//             return ResponseEntity.badRequest().body("Invalid currency");
//         }

//         return ResponseEntity.ok().body(formattedPrice);
//     } catch (Exception e) {
//         return ResponseEntity.status(500).body(null); // Internal server error
//     }
// }

@GetMapping("/total-fine-price/{currency}/{cpf}")
public ResponseEntity<String> getTotalFinePriceByCurrencyAndCpf(
        @PathVariable("currency") String currency,
        @PathVariable("cpf") String cpf) {
    try {
        Double totalFinePrice = calculateTotalFinePrice(cpf);
        Double convertedPrice = 0.0;
        String formattedPrice = "";

        if (currency.equalsIgnoreCase("dollar")) {
            convertedPrice = infractionService.convertCurrency(totalFinePrice, "BRL", "USD");
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            formattedPrice = nf.format(convertedPrice);
        } else if (currency.equalsIgnoreCase("euro")) {
            convertedPrice = infractionService.convertCurrency(totalFinePrice, "BRL", "EUR");
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
            formattedPrice = nf.format(convertedPrice);
        } else if (currency.equalsIgnoreCase("real")) {
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            formattedPrice = nf.format(totalFinePrice);

        } else {
            return ResponseEntity.badRequest().body("Invalid currency");
        }

        return ResponseEntity.ok().body(formattedPrice);
    } catch (Exception e) {
        // Tratar a exceção apropriadamente, por exemplo, logando-a
        e.printStackTrace();
        return ResponseEntity.status(500).body("Internal server error");
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


    // @GetMapping("/sex/{sex}")
    // public ResponseEntity<List<InfractionEntity>> getInfractionBySex(@PathVariable String sex) {
    //     try {
    //         List<InfractionEntity> infractions = infractionService.getInfractionBySex(sex);
    //         return ResponseEntity.ok().body(infractions);
    //     } catch (RuntimeException e) {
    //         log.error("Error retrieving infractions by sex: {}", e.getMessage());
    //         throw e;
    //     }
    // }

    // @GetMapping("/age/{age}")
    // public ResponseEntity<List<InfractionEntity>> getInfractionByAge(@PathVariable int age) {
    //     try {
    //         List<InfractionEntity> infractions = infractionService.getInfractionByAge(age);
    //         return ResponseEntity.ok().body(infractions);
    //     } catch (RuntimeException e) {
    //         log.error("Error retrieving infractions by age: {}", e.getMessage());
    //         throw e;
    //     }
    // }

    // @GetMapping("/most-common-violation-by-sex/{sex}")
    // public ResponseEntity<InfractionEntity> getMostCommonViolationBySex(@PathVariable String sex) {
    //     try {
    //         InfractionEntity mostCommonInfraction = infractionService.getMostCommonViolationBySex(sex);
    //         return ResponseEntity.ok().body(mostCommonInfraction);
    //     } catch (RuntimeException e) {
    //         log.error("Error retrieving most common infraction by sex: {}", e.getMessage());
    //         throw e;
    //     }
    // }

    // @GetMapping("/most-common-infraction-by-age/{age}")
    // public ResponseEntity<InfractionEntity> getMostCommonInfractionByAge(@PathVariable int age) {
    //     try {
    //         Optional<InfractionEntity> mostCommonInfraction = infractionService.getMostCommonInfractionByAge(age);

    //         if (mostCommonInfraction.isPresent()) {
    //             return ResponseEntity.ok().body(mostCommonInfraction.get());
    //         } else {
    //             return ResponseEntity.notFound().build();
    //         }
    //     } catch (RuntimeException e) {
    //         log.error("Error retrieving most common infraction by age: {}", e.getMessage());
    //         throw e;
    //     }
    // }




 @GetMapping("/latest/{cpf}")
    public ResponseEntity<InfractionEntity> getLatestInfractionByCPF(@PathVariable("cpf") String cpf) {
        List<InfractionEntity> allInfractionsForCpf = infractionService.getFinePriceByCpf(cpf);

        if (allInfractionsForCpf.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Optional<InfractionEntity> lastInfraction = allInfractionsForCpf.stream()
                .max(Comparator.comparing(infraction -> LocalDate.parse(infraction.getDate(), formatter)));

        return lastInfraction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    @GetMapping("/list-by-cpf/{currency}/{cpf}")
    public ResponseEntity<List<InfractionEntity>> getInfractionsByCpf(@PathVariable String currency, @PathVariable String cpf) {
        try {
            List<InfractionEntity> infractions = infractionService.getFinePriceByCpf(cpf);
            return ResponseEntity.ok(infractions);
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/list-non-paid/{cpf}")
    public ResponseEntity<List<InfractionEntity>> getNonPaidInfractionsByCpf(@PathVariable String cpf) {
        try {
            List<InfractionEntity> infractions = infractionService.getNotPaidFine(cpf);
            return ResponseEntity.ok(infractions);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



@PostMapping("/pay/{id}")
public ResponseEntity<InfractionEntity> payInfraction(@PathVariable String id) {
    try {
        ObjectId objectId;
        try {
            // Try parsing as ObjectId directly
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            try {
                // If it's not a valid ObjectId, try parsing it as a timestamp
                long timestamp = Long.parseLong(id);
                objectId = new ObjectId(Date.from(Instant.ofEpochMilli(timestamp)));
            } catch (NumberFormatException ex) {
                // If it's neither a valid ObjectId nor a timestamp, throw an exception
                throw new IllegalArgumentException("Invalid id format", ex);
            }
        }

        InfractionEntity infraction = infractionService.setAsPaid(objectId.toString());
        return ResponseEntity.ok(infraction);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

//setasAllAsPaidForCpf
@PostMapping("/pay-all/{cpf}")
public ResponseEntity<List<InfractionEntity>> payAllInfractions(@PathVariable String cpf) {
    try {
        List<InfractionEntity> infractions = infractionService.setAsPaidForCpf(cpf);
        return ResponseEntity.ok(infractions);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}




    @GetMapping("/list-paid/{cpf}")
    public ResponseEntity<List<InfractionEntity>> getPaidInfractionsByCpf(@PathVariable String cpf) {
        try {
            List<InfractionEntity> infractions = infractionService.searchAllPaidInfractions(cpf);
            return ResponseEntity.ok(infractions);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/allpaid")
    public ResponseEntity<List<InfractionEntity>> getAllPaidInfractions() {
        try {
            List<InfractionEntity> infractions = infractionService.allPaidInfractions();
            return ResponseEntity.ok(infractions);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }









}