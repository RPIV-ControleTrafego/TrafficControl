package com.infraction.serviceinfraction.service;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import com.infraction.serviceinfraction.dto.InfractionDTO;
import com.infraction.serviceinfraction.entity.InfractionEntity;
import com.infraction.serviceinfraction.logger.LoggerInfraction;
import com.infraction.serviceinfraction.repository.InfractionRepository;
import com.infraction.serviceinfraction.service.calculator.CurrencyConverterAdapter;
import com.infraction.serviceinfraction.service.calculator.FinePriceCalculator;
import com.infraction.serviceinfraction.service.calculator.GeneralFineCalculator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class InfractionService implements IinfractionService{
    private final LoggerInfraction log = new LoggerInfraction(InfractionService.class);
    private final CurrencyConverterAdapter currencyConverter;
    private FinePriceCalculator fineCalculator;
    @Autowired
    private InfractionRepository infractionRepository;
    @Autowired
    private InfractionEntity infractionEntity;
    @Autowired
    private GeneralFineCalculator generalFineCalculator = new GeneralFineCalculator();
    public InfractionService(FinePriceCalculator fineCalculator, CurrencyConverterAdapter currencyConverter) {
        this.fineCalculator = fineCalculator;
        this.currencyConverter = currencyConverter;
    }
    @Override
    public void newInfraction(InfractionDTO infractionInfo) {
        log.info("Traffic service - Received traffic information: {}", infractionInfo);
        try {
            infractionEntity = mapInfractionDTOToInfractionEntity(infractionInfo);
            boolean fineCalculated = calculateFine(infractionInfo);
            if (fineCalculated) {
                double finePrice = infractionInfo.getFinePrice();
                String violation = infractionInfo.getViolation();
                log.info("Traffic service - Fine price calculated: {} for violation: {}", finePrice, violation);
                infractionEntity.setFinePrice(finePrice);
            }
            infractionRepository.save(infractionEntity);
            log.info("Traffic service - Traffic info saved successfully: {}", infractionEntity);
        } catch (RuntimeException e) {
            log.error("Traffic service - Specific error saving traffic info: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Traffic service - Error saving traffic info: {}", e.getMessage());
        }
    }
    public List<InfractionEntity> getAllInfractions() {
        return infractionRepository.findAll();
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
    public List<InfractionEntity> getInfractionsByCpf(String cpf) {
        log.info("Traffic service - Retrieving infractions by CPF: {}", cpf);
        try {
            return infractionRepository.findInfractionByVeiculeOwnerCpf(cpf);
        } catch (RuntimeException e) {
            log.error("Traffic service - Error retrieving infractions by CPF: {}", e.getMessage());
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
    .age(infractionDTO.getAge())
    .sex(infractionDTO.getSex())
    .isPaid(infractionDTO.isPaid())
    .idInfraction(infractionDTO.getIdInfraction())
    .finePrice(infractionDTO.getFinePrice())
    .build();
}
private boolean calculateFine(InfractionDTO infractionDTO) {
    double finePrice = fineCalculator.calculateFine(infractionDTO);  
    infractionDTO.setFinePrice(finePrice);
    return finePrice > 0;
}
    public Double convertCurrency(Double amount, String fromCurrency, String toCurrency) {
        return currencyConverter.convertCurrency(amount, fromCurrency, toCurrency);
    }
    public Map<Integer, Integer> findPeakHours(List<InfractionEntity> infractions) {
        Map<Integer, Integer> hourFrequency = new HashMap<>();
        for (InfractionEntity infraction : infractions) {
            String dateStr = infraction.getDate();
            int hour = Integer.parseInt(dateStr.substring(11, 13)); 
            hourFrequency.put(hour, hourFrequency.getOrDefault(hour, 0) + 1);
        }
        int maxFrequency = 0;
        for (int frequency : hourFrequency.values()) {
            maxFrequency = Math.max(maxFrequency, frequency);
        }
        Map<Integer, Integer> peakHours = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : hourFrequency.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                peakHours.put(entry.getKey(), entry.getValue());
            }
        }
        return peakHours;
    }
    public Map<String, Integer> findPeakDays(List<InfractionEntity> infractions) {
        Map<String, Integer> dayFrequency = new HashMap<>();
        for (InfractionEntity infraction : infractions) {
            String dateStr = infraction.getDate();
            String day = dateStr.substring(0, 10); 
            dayFrequency.put(day, dayFrequency.getOrDefault(day, 0) + 1);
        }
        int maxFrequency = 0;
        for (int frequency : dayFrequency.values()) {
            maxFrequency = Math.max(maxFrequency, frequency);
        }
        Map<String, Integer> peakDays = new HashMap<>();
        for (Map.Entry<String, Integer> entry : dayFrequency.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                peakDays.put(entry.getKey(), entry.getValue());
            }
        }
        return peakDays;
    }
    public String findInfractionByViolation(String violation){
        List<InfractionEntity> infractions = infractionRepository.findInfractionByViolations(violation);
        try {
            infractions = infractionRepository.findInfractionByViolations(violation);
            return infractions.toString();
        }
        catch (RuntimeException e) {
            log.error("Traffic service - Error retrieving infractions with car plate: {}", e.getMessage());
            throw e;
        }
    }
    public float calculatePercentage(List<InfractionEntity> infractions) {
        try {
            return (infractions.size() * 100) / 1000;
        } catch (RuntimeException e) {
            log.error("Traffic service - Error retrieving infractions: {}", e.getMessage());
            throw e;
        }
    }
    public float percentageInfractionBySex(String sex) {
        List<InfractionEntity> infractions = infractionRepository.findAll();
        return calculatePercentage(infractions);
    }
    public float percentageInfractionByAge(String age) {
        List<InfractionEntity> infractions = infractionRepository.findAll();
        return calculatePercentage(infractions);
    }
    public float percentageInfractionByCarType(String carType) {
        List<InfractionEntity> infractions = infractionRepository.findAll();
        return calculatePercentage(infractions);
    }
    public float percentageInfractionByCarColor(String carColor) {
        List<InfractionEntity> infractions = infractionRepository.findAll();
        return calculatePercentage(infractions);
    }
    public float percentageInfractionByCarBrand(String carBrand) {
        List<InfractionEntity> infractions = infractionRepository.findAll();
        return calculatePercentage(infractions);
    }
    public List<InfractionEntity> getInfractionByVeiculeOwnerCpf(String parameter) {
        return null;
    }
    public List<InfractionEntity> getInfractionBySex(String sex)
    {
        try{
              List<InfractionEntity> infractions = infractionRepository.findViolationBySex(sex);
        return infractions;
        }catch (RuntimeException e) {
            log.error("Traffic service - Error retrieving infractions with car plate: {}", e.getMessage());
            throw e;
        }
    }
    public List<InfractionEntity> getInfractionByAge(int age) {
        try{
            List<InfractionEntity> infractions = infractionRepository.findViolationByAge(age);
      return infractions;
      }catch (RuntimeException e) {
          log.error("Traffic service - Error retrieving infractions with car plate: {}", e.getMessage());
          throw e;
      }
    }
public InfractionEntity getLastFineByCpf(String cpf) {
    List<InfractionEntity> allInfractionsForCpf = getFinePriceByCpf(cpf);
    if (allInfractionsForCpf.isEmpty()) {
        return null;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Optional<InfractionEntity> lastInfraction = allInfractionsForCpf.stream()
            .max(Comparator.comparing(infraction -> LocalDate.parse(infraction.getDate(), formatter)));
    return lastInfraction.orElse(null);
}
public List<InfractionEntity> getFinePriceByDate(String date) {
    try {
        return infractionRepository.findByDate(date);
    } catch (RuntimeException e) {
        log.error("Traffic service - Error retrieving infractions with car plate: {}", e.getMessage());
        throw e;
    }
}
public List<InfractionEntity> getNotPaidFine(String cpf) {
    try {
        return infractionRepository.findByIsPaidFalseAndCpf(cpf);
    } catch (RuntimeException e) {
        log.error("Traffic service - Error retrieving infractions with car plate: {}", e.getMessage());
        throw e;
    }
}
public InfractionEntity setAsPaid(String id) {
    try {
        Optional<InfractionEntity> optionalInfraction = infractionRepository.findByIdInfraction(id);
        if (optionalInfraction.isPresent()) {
            InfractionEntity infraction = optionalInfraction.get();
            if (!infraction.isPaid()) {
                infraction.setPaid(true);
                return infractionRepository.save(infraction);
            } else {
                log.info("Traffic service - Infraction already paid with id: {}", id);
                return infraction;
            }
        } else {
            log.info("Traffic service - Infraction not found with id: {}", id);
            return null;
        }
    } catch (IllegalArgumentException e) {
        log.error("Traffic service - Invalid idInfraction: {}", e.getMessage());
        throw new IllegalArgumentException("Invalid idInfraction", e);
    }
}
public List<InfractionEntity> searchAllPaidInfractions(String cpf) {
    log.info("Searching for paid infractions for CPF: {}", cpf);
    try {
        List<InfractionEntity> infractions = infractionRepository.findByIsPaidTrueAndCpf(cpf);
        log.info("Found {} paid infractions.", infractions.size());
        return infractions;
    } catch (RuntimeException e) {
        log.error("Traffic service - Error retrieving infractions with car plate: {}", e.getMessage());
        throw e;
    }
}
public List<InfractionEntity> setAsPaidForCpf(String cpf) {
    try {
        List<InfractionEntity> infractions = infractionRepository.findByIsPaidFalseAndCpf(cpf);
        if (infractions.isEmpty()) {
            log.info("Traffic service - No unpaid infractions found for CPF: {}", cpf);
            return Collections.emptyList();
        }
        for (InfractionEntity infraction : infractions) {
            infraction.setPaid(true);
        }
        return infractionRepository.saveAll(infractions);
    } catch (RuntimeException e) {
        log.error("Traffic service - Error retrieving infractions with car plate: {}", e.getMessage());
        throw e;
    }
}
public List<InfractionEntity> allPaidInfractions(){
    log.info("Searching for paid infractions");
    try {
        List<InfractionEntity> infractions = infractionRepository.findByIsPaidTrue();
        log.info("Found {} paid infractions.", infractions.size());
        return infractions;
    } catch (RuntimeException e) {
        log.error("Traffic service - Error retrieving infractions with car plate: {}", e.getMessage());
        throw e;
    }
}
}
