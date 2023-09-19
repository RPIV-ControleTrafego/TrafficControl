package com.infraction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.infraction.serviceinfraction.calculator.FinePriceCalculator;
import com.infraction.serviceinfraction.dto.InfractionDTO;



public class FinePriceTest {

        
     @Test
    void testCalculateFinePriceSpeeding1() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("speeding");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(45.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateSpeedingFine2() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setSpeed(100); 
        infractionDTO.setMaxSpeed(80); 

        double speedingFine = FinePriceCalculator.calculateFinePrice("speeding", infractionDTO);

     
      
        double expectedFine = (100 - 80) * 45.0;
        assertEquals(expectedFine, speedingFine);
    }

    @Test
    void testCalculateSpeedingAndWrongDirection() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setSpeed(100); 
        infractionDTO.setMaxSpeed(80); 
        infractionDTO.setViolation("wrong direction");

        double expectedSpeedingFine = (100 - 80) * FinePriceCalculator.calculateFinePrice("speeding");
        double expectedWrongDirectionFine = FinePriceCalculator.calculateFinePrice("wrong direction");

    
        double expectedTotalFine = expectedSpeedingFine + expectedWrongDirectionFine;

        double totalFine = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(expectedTotalFine, totalFine);
    }

    @Test
    void testCalculateFinePriceNoViolation() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setSpeed(100); 
        infractionDTO.setMaxSpeed(180); 

        double speedingFine = FinePriceCalculator.calculateFinePrice("speeding", infractionDTO);

        assertEquals(0.0, speedingFine);
    }

    @Test
    void testCalculateFinePriceNoViolation2() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setSpeed(100); 
        infractionDTO.setMaxSpeed(180); 
        infractionDTO.setViolation("no violation");

        double speedingFine = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(0.0, speedingFine);
    }

    
    @Test
    void testCalculateFinePriceNoViolation3() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setSpeed(100); 
        infractionDTO.setMaxSpeed(180); 
        infractionDTO.setViolation(null);

        double speedingFine = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(0.0, speedingFine);
    }

    
    @Test
    void testCalculateNoPlate(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no plate");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(300.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }


    @Test
    void testCalculateNoPlateAndSpeeding(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no plate");
        infractionDTO.setSpeed(200); 
        infractionDTO.setMaxSpeed(180); 

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1200.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }


    @Test
    void testCalculateRedLight(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("red light");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(150.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateRedLightAndSpeeding(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("red light");
        infractionDTO.setSpeed(200); 
        infractionDTO.setMaxSpeed(180); 

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1050.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateStopSign(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("stop sign");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(120.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateStopSignAndSpeeding(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("stop sign");
        infractionDTO.setSpeed(200); 
        infractionDTO.setMaxSpeed(180); 

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1020.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateNoSeatBelt(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no seat belt");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(80.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateNoSeatBeltAndSpeeding(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no seat belt");
        infractionDTO.setSpeed(200); 
        infractionDTO.setMaxSpeed(180); 

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(980.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    // @Test
    // void testCalculateNoHelmet(){
    //     InfractionDTO infractionDTO = new InfractionDTO();
    //     infractionDTO.setViolation("no helmet");

    //     double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

    //     assertEquals(90.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    // }

    @Test
    void testCalculateNoInsurance(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no insurance");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(150.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateNoInsuranceAndSpeeding(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no insurance");
        infractionDTO.setSpeed(200); 
        infractionDTO.setMaxSpeed(180); 

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1050.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateNoRegistration(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no registration");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(100.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateNoRegistrationAndSpeeding(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no registration");
        infractionDTO.setSpeed(200); 
        infractionDTO.setMaxSpeed(180); 

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1000.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateDrunkDriving(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("drunk driving");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(500.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateDrunkDrivingAndSpeeding(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("drunk driving");
        infractionDTO.setSpeed(200); 
        infractionDTO.setMaxSpeed(180); 

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1400.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateRecklessDriving(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("reckless driving");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(400.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateRecklessDrivingAndSpeeding(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("reckless driving");
        infractionDTO.setSpeed(200); 
        infractionDTO.setMaxSpeed(180); 

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1300.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateHitAndRun(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("hit and run");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(600.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateHitAndRunAndSpeeding(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("hit and run");
        infractionDTO.setSpeed(200); 
        infractionDTO.setMaxSpeed(180); 

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);
        
        assertEquals(1500.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateTailgating(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("tailgating");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(100.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }

    @Test
    void testCalculateTailgatingAndSpeeding(){
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("tailgating");
        infractionDTO.setSpeed(200); 
        infractionDTO.setMaxSpeed(180); 

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);
        
        assertEquals(1000.0, finePrice); // Assuming speeding fine is 45.0 as per your code
    }






}


// package com.infraction.serviceinfraction.calculator;

// import java.util.HashMap;
// import java.util.Map;

// import com.infraction.serviceinfraction.dto.InfractionDTO;

// public class FinePriceCalculator {
//     private static final Map<String, Double> finePrices = new HashMap<>();

//     static {
//         finePrices.put("red light", 150.0);
//         finePrices.put("stop sign", 120.0);
//         finePrices.put("no seat belt", 80.0);
//         finePrices.put("no helmet", 90.0);
//         finePrices.put("no license", 200.0);
//         finePrices.put("no insurance", 150.0);
//         finePrices.put("no registration", 100.0);
//         finePrices.put("drunk driving", 500.0);
//         finePrices.put("reckless driving", 400.0);
//         finePrices.put("hit and run", 600.0);
//         finePrices.put("tailgating", 100.0);
//         finePrices.put("jaywalking", 50.0);
//         finePrices.put("illegal turn", 80.0);
//         finePrices.put("parking violation", 100.0);
//         finePrices.put("speeding", 45.0);
//         finePrices.put("wrong direction", 25.0);
//         finePrices.put("no plate", 300.0);
//     }

//     public static double calculateFinePrice(String violation, InfractionDTO infractionDTO) {
//         if (infractionDTO == null) {
//             throw new IllegalArgumentException("InfractionDTO não pode ser nulo.");
//         }

//         double finePrice = 0;

//         if (infractionDTO.getSpeed() > infractionDTO.getMaxSpeed()) {
//             finePrice = calculateSpeedingFine(infractionDTO);
//         }

//         if (infractionDTO.getViolation() != null) {
//             finePrice += calculateFinePrice(infractionDTO.getViolation().toLowerCase());
//         }

//         return finePrice;
//     }

//     private static double calculateSpeedingFine(InfractionDTO infractionDTO) {
//         return Math.max(0, infractionDTO.getSpeed() - infractionDTO.getMaxSpeed()) * finePrices.get("speeding");
//     }

//     public static double calculateFinePrice(String violation) {
//         return finePrices.getOrDefault(violation, 0.0);
//     }

// }
