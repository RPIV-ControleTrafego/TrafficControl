package com.accident.serviceaccident.Generator;

import java.util.Random;
import java.time.LocalDate;

public class AccidentGenerator {

    private String accidentId;
    private String severity;
    private String date;
    private boolean hasInjuries;
    private boolean hasFatalities;
    private String address;
    private String description;
    private String type;
    private String hasInfraction;

    private static Random geradorAccidentID = new Random();
    private static Random geradorSeverity = new Random();
    private static Random geradorDate = new Random();
    private static Random geradorHasInjuries = new Random();
    private static Random geradorHasFatalities = new Random();
    private static Random geradorAddres = new Random();
    private static Random geradorDescription = new Random();
    private static Random geradorType = new Random();
    private static Random geradorHasInfraction = new Random();

    
}
