package com.infraction.serviceinfraction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.infraction.serviceinfraction.service.calculator.FinePriceCalculator;
import com.infraction.serviceinfraction.dto.InfractionDTO;

public class FinePriceTest {


    FinePriceCalculator calculator = new FinePriceCalculator();

    @Test
    void testCalculateFinePriceSpeeding1() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("speeding");

        double finePrice = calculator.calculateFine(infractionDTO);

        assertEquals(45.0, finePrice); 
    }

    @Test
    void testCalculateSpeedingFine2() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setSpeed(100);
        infractionDTO.setMaxSpeed(80);

        double speedingFine =  calculator.calculateFine(infractionDTO);

        double expectedFine = (100 - 80) * 45.0;
        assertEquals(expectedFine, speedingFine);
    }

    // @Test
    // void testCalculateSpeedingAndWrongDirection() {
    //     InfractionDTO infractionDTO = new InfractionDTO();
    //     infractionDTO.setSpeed(100);
    //     infractionDTO.setMaxSpeed(80);
    //     infractionDTO.setViolation("wrong direction");

    //     double expectedSpeedingFine = (100 - 80) *  calculator.calculateFine("speeding");
    //     double expectedWrongDirectionFine = FinePriceCalculator.calculateFinePrice("wrong direction");

    //     double expectedTotalFine = expectedSpeedingFine + expectedWrongDirectionFine;

    //     double totalFine = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

    //     assertEquals(expectedTotalFine, totalFine);
    // }

    @Test
    void testCalculateFinePriceNoViolation() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setSpeed(100);
        infractionDTO.setMaxSpeed(180);

        double speedingFine = calculator.calculateFine( infractionDTO);

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
    void testCalculateNoPlate() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no plate");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(300.0, finePrice); 
    }

    @Test
    void testCalculateNoPlateAndSpeeding() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no plate");
        infractionDTO.setSpeed(200);
        infractionDTO.setMaxSpeed(180);

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1200.0, finePrice); 
    }

    @Test
    void testCalculateRedLight() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("red light");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(150.0, finePrice); 
    }

    @Test
    void testCalculateRedLightAndSpeeding() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("red light");
        infractionDTO.setSpeed(200);
        infractionDTO.setMaxSpeed(180);

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1050.0, finePrice); 
    }

    @Test
    void testCalculateStopSign() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("stop sign");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(120.0, finePrice);
    }

    @Test
    void testCalculateStopSignAndSpeeding() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("stop sign");
        infractionDTO.setSpeed(200);
        infractionDTO.setMaxSpeed(180);

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1020.0, finePrice); 
    }

    @Test
    void testCalculateNoSeatBelt() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no seat belt");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(80.0, finePrice);
    }

    @Test
    void testCalculateNoSeatBeltAndSpeeding() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no seat belt");
        infractionDTO.setSpeed(200);
        infractionDTO.setMaxSpeed(180);

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(980.0, finePrice);
    }

    // @Test
    // void testCalculateNoHelmet(){
    // InfractionDTO infractionDTO = new InfractionDTO();
    // infractionDTO.setViolation("no helmet");

    // double finePrice =
    // FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(),
    // infractionDTO);

    // assertEquals(90.0, finePrice); // Assuming speeding fine is 45.0 as per your
    // code
    // }

    @Test
    void testCalculateNoInsurance() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no insurance");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(150.0, finePrice); 
    }

    @Test
    void testCalculateNoInsuranceAndSpeeding() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no insurance");
        infractionDTO.setSpeed(200);
        infractionDTO.setMaxSpeed(180);

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1050.0, finePrice); 
    }

    @Test
    void testCalculateNoRegistration() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no registration");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(100.0, finePrice); 
    }

    @Test
    void testCalculateNoRegistrationAndSpeeding() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("no registration");
        infractionDTO.setSpeed(200);
        infractionDTO.setMaxSpeed(180);

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1000.0, finePrice); 
    }

    @Test
    void testCalculateDrunkDriving() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("drunk driving");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(500.0, finePrice); 
    }

    @Test
    void testCalculateDrunkDrivingAndSpeeding() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("drunk driving");
        infractionDTO.setSpeed(200);
        infractionDTO.setMaxSpeed(180);

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1400.0, finePrice); 
    }

    @Test
    void testCalculateRecklessDriving() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("reckless driving");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(400.0, finePrice); 
    }

    @Test
    void testCalculateRecklessDrivingAndSpeeding() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("reckless driving");
        infractionDTO.setSpeed(200);
        infractionDTO.setMaxSpeed(180);

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1300.0, finePrice); 
    }

    @Test
    void testCalculateHitAndRun() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("hit and run");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(600.0, finePrice); 
    }

    @Test
    void testCalculateHitAndRunAndSpeeding() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("hit and run");
        infractionDTO.setSpeed(200);
        infractionDTO.setMaxSpeed(180);

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1500.0, finePrice); 
    }

    @Test
    void testCalculateTailgating() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("tailgating");

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(100.0, finePrice); 
    }

    @Test
    void testCalculateTailgatingAndSpeeding() {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setViolation("tailgating");
        infractionDTO.setSpeed(200);
        infractionDTO.setMaxSpeed(180);

        double finePrice = FinePriceCalculator.calculateFinePrice(infractionDTO.getViolation(), infractionDTO);

        assertEquals(1000.0, finePrice); 
    }

}
