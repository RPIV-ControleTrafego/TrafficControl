package com.infraction.serviceinfraction.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.entity.InfractionEntity;
import com.infraction.serviceinfraction.repository.InfractionRepository;
import com.infraction.serviceinfraction.service.calculator.FinePriceCalculator;
import com.infraction.serviceinfraction.service.calculator.GeneralFineCalculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.html.parser.Entity;

import org.json.JSONObject;
import org.slf4j.Logger;

@Service
public class InfractionService implements IinfractionService{

  private Logger log;

    //Strategy Pattern  
    private FinePriceCalculator fineCalculator;

    @Autowired
    private InfractionRepository infractionRepository;

    @Autowired
    private InfractionEntity infractionEntity;

    @Autowired
    private GeneralFineCalculator generalFineCalculator = new GeneralFineCalculator();

 
    public InfractionService(FinePriceCalculator fineCalculator) {
        this.fineCalculator = fineCalculator;
    }


    @Override
    public void newInfraction(InfractionDTO infractionInfo) {
        log.info("Traffic service - Received traffic information: {}", infractionInfo);
    
        try {
            InfractionEntity infractionEntity = mapInfractionDTOToInfractionEntity(infractionInfo);
            double finePrice = generalFineCalculator.calculateFine(infractionInfo);
            log.info("Traffic service - Fine price calculated: {} for violation: {}", finePrice, infractionInfo.getViolation());
            infractionEntity.setFinePrice(finePrice);
    
            infractionRepository.save(infractionEntity);
            log.info("Traffic service - Traffic info saved successfully: {}", infractionEntity);
        } catch (RuntimeException e) {
            log.error("Traffic service - Specific error saving traffic info: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Traffic service - Error saving traffic info: {}", e.getMessage());
        }
    }

public List<InfractionEntity> getInfractionsInDate(String date) {
    log.info("Traffic service - Retrieving infractions for date: {}", date);

    try {
        return infractionRepository.findByDateGreaterThanEqual(date);
    } catch (RuntimeException e) {
        log.error("Traffic service - Error retrieving infractions for date: {}", e.getMessage());
        throw e;
    }
}


public List<InfractionEntity> getSpeedInfractionGreaterThan(int speed) {
    log.info("Traffic service - Retrieving infractions with speed greater than: {}", speed);

    try {
        return infractionRepository.findSpeedInfractioGreaterThan(speed);
    } catch (RuntimeException e) {
        log.error("Traffic service - Error retrieving infractions with speed greater than: {}", e.getMessage());
        throw e;
    }
}

public List<InfractionEntity> getSpeedInfractionLowerThan(int speed) {
    log.info("Traffic service - Retrieving infractions with speed lower than: {}", speed);

    try {
        return infractionRepository.findSpeedInfractionLowerThan(speed);
    } catch (RuntimeException e) {
        log.error("Traffic service - Error retrieving infractions with speed lower than: {}", e.getMessage());
        throw e;
    }
}   



    public List<InfractionEntity> getFinePriceByCarPlate(String carPlate) {
        log.info("Traffic service - Retrieving infractions with car plate: {}", carPlate);
    
        try {
            return infractionRepository.findByCarPlate(carPlate);
        } catch (RuntimeException e) {
            log.error("Traffic service - Error retrieving infractions with car plate: {}", e.getMessage());
            throw e;
        }
    }


    public List<InfractionEntity> getFinePriceByCpf(String cpf) {
        log.info("Traffic service - Retrieving infractions with car plate: {}", cpf);
    
        try {
            return infractionRepository.findInfractionByVeiculeOwnerCpf(cpf);
        } catch (RuntimeException e) {
            log.error("Traffic service - Error retrieving infractions with car plate: {}", e.getMessage());
            throw e;
        }
    }

 public Double getTotalFinePriceByCpf(String cpf) {
        log.info("Traffic service - Retrieving total fine price with cpf: {}", cpf);
    
        try {
            List<InfractionEntity> infractions = infractionRepository.findInfractionByVeiculeOwnerCpf(cpf);
            Double totalFinePrice = infractions.stream()
                    .mapToDouble(InfractionEntity::getFinePrice)
                    .sum();
    
            return totalFinePrice;
        } catch (RuntimeException e) {
            log.error("Traffic service - Error retrieving total fine price with cpf: {}", e.getMessage());
            throw e;
        }
    }



private InfractionEntity mapInfractionDTOToInfractionEntity(InfractionDTO infractionDTO){
    return InfractionEntity.builder()
    .carPlate(infractionDTO.getCarPlate())
    .addres(infractionDTO.getAddress())
    .date(infractionDTO.getDate())
    .violation(infractionDTO.getViolation())
    .carType(infractionDTO.getCarType())
    .carColor(infractionDTO.getCarColor())
    .carBrand(infractionDTO.getCarBrand())
    .veiculeOwnerName(infractionDTO.getVeiculeOwnerName())
    .veiculeOwneCPF(infractionDTO.getVeiculeOwneCPF())
    .speed(infractionDTO.getSpeed())
    .maxSpeed(infractionDTO.getMaxSpeed())
    .build();
}

private boolean calculateFine(InfractionDTO infractionDTO) {
    double finePrice = fineCalculator.calculateFine(infractionDTO);  // Usando a estratégia de cálculo de multa apropriada
    infractionDTO.setFinePrice(finePrice);
    return finePrice > 0;
}





   private static final String API_KEY = "qOpahvu71xPZOBX1";
    private static final String API_SECRET = "pgXMITMRfnK7vBdPUdI3VU605yMq0jx7";
    private static final String BASE_URL = "https://api.coinbase.com/v2/";

    public Double convertCurrency(Double amount, String fromCurrency, String toCurrency) {
        try {
            String path = String.format("prices/%s-%s/spot", fromCurrency, toCurrency);
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    
            String message = timestamp + "GET" + path;
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(API_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] hmacBytes = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String signature = bytesToHex(hmacBytes);
    
            String urlString = BASE_URL + path;
    
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("CB-ACCESS-KEY", API_KEY);
            conn.setRequestProperty("CB-ACCESS-SIGN", signature);
            conn.setRequestProperty("CB-ACCESS-TIMESTAMP", timestamp);
    
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
    
            // Parse the response to get the exchange rate
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject data = jsonResponse.getJSONObject("data");
            String amountStr = data.getString("amount");
            Double exchangeRate = Double.parseDouble(amountStr);
    
            return amount * exchangeRate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }


 }



