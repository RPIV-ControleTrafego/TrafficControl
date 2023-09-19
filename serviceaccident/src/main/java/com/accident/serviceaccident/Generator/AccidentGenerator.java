package com.accident.serviceaccident.Generator;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class AccidentGenerator {

    private String severity;
    private String date;
    private boolean hasInjuries;
    private boolean hasFatalities;
    private String address;
    private String description;
    private String type;
    private boolean hasInfraction;

    private static Random geradorSeverity = new Random();
    private static Random geradorDate = new Random();
    private static Random geradorHasInjuries = new Random();
    private static Random geradorHasFatalities = new Random();
    private static Random geradorAddress = new Random();
    private static Random geradorDescription = new Random();
    private static Random geradorType = new Random();
    private static Random geradorHasInfraction = new Random();

    private String[] streets = {"Main Street", "Elm Street", "Maple Avenue", "Oak Lane", "Cedar Road"};
    private String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Miami"};
    private String[] states = {"CA", "NY", "TX", "FL", "IL"};
    private String[] zipCodes = {"10001", "90001", "60601", "77001", "33101"};
    private String[] descriptions = {"Accident", "Collision", "Crash", "Wreck", "Smash", "Bump", "Fender Bender", "Pileup", "Wreckage", "Wrecking"};
    private String[] types = {"Frontal Colision",  "Lateral Colision", "capote colision"};

    public AccidentGenerator() {
        geraSeverity();
        geraDate();
        geraInjuries();
        geraFatalities();
        geraAddress();
        geraDescription();
        geraType();
        geraInfraction();
    }

    private void geraSeverity(){
        this.severity = Integer.toString(geradorSeverity.nextInt(5) + 1);
    }
    
    public String getSeverity(){
        return this.severity;
    }

    private void geraInjuries(){
        
        double randonValue = geradorHasInjuries.nextDouble();

        double desiredRate = 0.6;

        if (randonValue < desiredRate){
            this.hasInjuries = true;
        } else {
            this.hasInjuries = false;
        }
    }

    public boolean getHasInjuries(){
        return this.hasInjuries;
        }

        private void geraFatalities(){
        
        double randonValue = geradorHasFatalities.nextDouble();

        double desiredRate = 0.2;

        if (randonValue < desiredRate){
            this.hasFatalities = true;
        } else {
            this.hasFatalities = false;
        }
    }

    public boolean getHasFatalities(){
        return this.hasFatalities;
        }

        private void geraInfraction(){
        
        double randonValue = geradorHasInfraction.nextDouble();

        double desiredRate = 0.4;

        if (randonValue < desiredRate){
            this.hasInfraction = true;
        } else {
            this.hasInfraction = false;
        }
    }

    public boolean getHasInfraction(){
        return this.hasInfraction;
        }

    private void geraType(){
        this.type = types[geradorType.nextInt(types.length)];
    }

    public String getType(){
        return this.type;
    }
    
    private void geraDescription(){
        this.description = descriptions[geradorDescription.nextInt(descriptions.length)];
    }

    public String getDescription(){
        return this.description;
    }

    private void geraDate() {
        // Gere uma data aleatória entre 01/01/2010 e 31/12/2023
        int minYear = 2010;
        int maxYear = 2023;

        int year = geradorDate.nextInt(maxYear - minYear + 1) + minYear;
        int month = geradorDate.nextInt(12) + 1;
        int day = geradorDate.nextInt(31) + 1;  

        this.date = LocalDate.of(year, month, day).toString();
    }

    public String getDate(){
        return this.date;
    }

    private void geraAddress() {
        String street = streets[geradorAddress.nextInt(streets.length)];
        String city = cities[geradorAddress.nextInt(cities.length)];
        String state = states[geradorAddress.nextInt(states.length)];
        String zipCode = zipCodes[geradorAddress.nextInt(zipCodes.length)];

        this.address = street + ", " + city + ", " + state + " " + zipCode;
    }

    public String getAddress(){
        return this.address;
    }

}
