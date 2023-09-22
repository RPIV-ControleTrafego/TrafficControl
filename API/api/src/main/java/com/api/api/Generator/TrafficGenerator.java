package com.api.api.Generator;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.time.LocalDate;




public class TrafficGenerator {
   
    
    private String carPlate;
    private String carType;
    private String carColor;
    private String carBrand;
    private String vehicleOwnerName;
    private String vehicleOwnerSurName;
    private String vehicleOwnerCPF;
    private String time;
    private String date;
    private String address;
    private double speed;
    private int maxSpeed;
     private String violation;
     private double maxSpeedGenerated = 250; // Velocidade máxima 
    private String direction; // north, south, east, west

    private String streetDirection; 
    
   
    private static Random geradorCarPlate = new Random();
    private static Random geradorCarType = new Random();
    private static Random geradorCarColor = new Random();
    private static Random geradorCarBrand = new Random();
    private static Random geradorOwnerName = new Random();
    private static Random geradorOwnerSurName = new Random();
    private static Random geradorDate = new Random();
    private static Random geradorMaxSpeed = new Random();
    private static Random geradorAddress = new Random();
    private static Random geradorDirection = new Random();
    private static Random geradorStreetDirection = new Random();
    private static Random geradorTime = new Random();
    private static Random geradorSpeed = new Random();
    private static Random geradorViolation = new Random();  




    private String[] streets = {"Main Street", "Elm Street", "Maple Avenue", "Oak Lane", "Cedar Road"};
    private String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Miami"};
    private String[] states = {"CA", "NY", "TX", "FL", "IL"};
    private String[] zipCodes = {"10001", "90001", "60601", "77001", "33101"};
     private String[] carTypes = {"hatch", "sedan", "SUV", "crossover", "pickup"};
    private String[] carColors = {"red", "blue", "black", "white", "silver", "gray", "brown", "yellow", "green"};
    private String[] carBrands = {"Ford", "Toyota", "Chevrolet", "Peugeot", "Renault", "Mazda", "Lexus", "BYD", "Mitsubishi"};
    private String[] ownerNames = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Helen", "Ivy", "Jack", "Liam", "Mia", "Noah", "Olivia", "Sophia"};
    private String[] ownerSurNames = {"Smith", "Johnson", "Brown", "Lee", "Wilson", "Davis", "Martinez", "Garcia", "Anderson", "Taylor", "Moore", "Jackson", "White", "Harris", "Clark"};
    private String[] streetDirections = {"north", "south", "east", "west"};
    private String[] directions = {"north", "south", "east", "west"};
    private int[] maxSpeedsList = {60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260};
    private String[] violationList = {"red light", "stop sign", "no seat belt", "no helmet",  "no license", "no insurance", "no registration", "drunk driving", "reckless driving", "hit and run", "tailgating", "jaywalking", "illegal turn", "parking violation"}; 
    
    private static final Map<String, Double> carBrandPollution = new HashMap<>();

    static{
        carBrandPollution.put("Ford", 0.5);
        carBrandPollution.put("Toyota", 0.4);
        carBrandPollution.put("Chevrolet", 0.3);
        carBrandPollution.put("Peugeot", 0.2);
        carBrandPollution.put("Renault", 0.1);
        carBrandPollution.put("Mazda", 0.2);
        carBrandPollution.put("Lexus", 0.3);
        carBrandPollution.put("BYD", 0.4);
        carBrandPollution.put("Mitsubishi", 0.5);

    }
    
    private static final Map<String, Double> carTypePollution = new HashMap<>();

    static{
        carTypePollution.put("hatch", 0.5);
        carTypePollution.put("sedan", 0.4);
        carTypePollution.put("SUV", 0.3);
        carTypePollution.put("crossover", 0.2);
        carTypePollution.put("pickup", 0.1);
    }

    private static final Map<String, Map<String, Double>> carBrandTypePollution = new HashMap<>();

static {
    // Ford possui os tipos sedan e hatch
    Map<String, Double> fordPollution = new HashMap<>();
    fordPollution.put("sedan", 0.5);
    fordPollution.put("hatch", 0.4);
    carBrandTypePollution.put("Ford", fordPollution);

    // Toyota possui outros tipos, por exemplo
    Map<String, Double> toyotaPollution = new HashMap<>();
    toyotaPollution.put("sedan", 0.3);
    toyotaPollution.put("SUV", 0.2);
    carBrandTypePollution.put("Toyota", toyotaPollution);

    // Chevrolet possui os tipos SUV e pickup
    Map<String, Double> chevroletPollution = new HashMap<>();
    chevroletPollution.put("SUV", 0.4);
    chevroletPollution.put("pickup", 0.3);
    carBrandTypePollution.put("Chevrolet", chevroletPollution);

    // Peugeot possui apenas o tipo sedan
    Map<String, Double> peugeotPollution = new HashMap<>();
    peugeotPollution.put("sedan", 0.2);
    carBrandTypePollution.put("Peugeot", peugeotPollution);

    // Renault possui apenas o tipo hatch
    Map<String, Double> renaultPollution = new HashMap<>();
    renaultPollution.put("hatch", 0.3);
    carBrandTypePollution.put("Renault", renaultPollution);
    
    // Mazda possui os tipos sedan e crossover
    Map<String, Double> mazdaPollution = new HashMap<>();
    mazdaPollution.put("sedan", 0.4);
    mazdaPollution.put("crossover", 0.3);
    carBrandTypePollution.put("Mazda", mazdaPollution);
    
    // Lexus possui apenas o tipo SUV
    Map<String, Double> lexusPollution = new HashMap<>();
    lexusPollution.put("SUV", 0.5);
    carBrandTypePollution.put("Lexus", lexusPollution);
    
    // BYD possui apenas o tipo hatch
    Map<String, Double> bydPollution = new HashMap<>();
    bydPollution.put("hatch", 0.4);
    carBrandTypePollution.put("BYD", bydPollution);
    
    // Mitsubishi possui apenas o tipo SUV
    Map<String, Double> mitsubishiPollution = new HashMap<>();
    mitsubishiPollution.put("SUV", 0.5);
    carBrandTypePollution.put("Mitsubishi", mitsubishiPollution);
}



    public TrafficGenerator() {
        geraCarPlate();
        geraCarType();
        geraCarColor();
        geraCarBrand();
        geraVehicleOwnerName();
        geraVehicleOwnerSurName();
        geraDate();
        geraSpeed();
        geraAddress();
        geraMaxSpeed();
        geraTime();
        geraDate();
        geraDirection();
        geraStreetDirection();
        geraViolation();
        geraOwnerCPF();
        calculatePollutionLevel(carType, carBrand);
    }

  

    private void geraCarPlate() {
      
        StringBuilder plate = new StringBuilder();

         double randomValue = geradorViolation.nextDouble();
    
      
        double desiredRate = 0.09;

        if (randomValue < desiredRate) {
            geraCarPlateEmpty();
            return;
        }

      
        for (int i = 0; i < 3; i++) {
            char randomLetter = (char) (geradorCarPlate.nextInt(26) + 'A');
            plate.append(randomLetter);
        }
        
      
        plate.append('-');
        
     
        for (int i = 0; i < 4; i++) {
            int randomDigit = geradorCarPlate.nextInt(10);
            plate.append(randomDigit);
        }
        
        this.carPlate = plate.toString();
    
        
    }
    
    private void geraCarPlateEmpty() {
        this.carPlate = null; 
    }


    private void geraOwnerCPF(){
        StringBuilder cpf = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int randomDigit = geradorCarPlate.nextInt(10);
            cpf.append(randomDigit);
        }
        cpf.append('.');
        for (int i = 0; i < 3; i++) {
            int randomDigit = geradorCarPlate.nextInt(10);
            cpf.append(randomDigit);
        }
        cpf.append('.');
        for (int i = 0; i < 3; i++) {
            int randomDigit = geradorCarPlate.nextInt(10);
            cpf.append(randomDigit);
        }
        cpf.append('-');
        for (int i = 0; i < 2; i++) {
            int randomDigit = geradorCarPlate.nextInt(10);
            cpf.append(randomDigit);
        }
        this.vehicleOwnerCPF = cpf.toString();
    }

    public String getVehicleOwnerCPF() {
        return vehicleOwnerCPF;
    }


    private void geraTime() {
        //formato "HH:mm:ss"
        int hour = geradorTime.nextInt(24);
        int minute = geradorTime.nextInt(60);
        int second = geradorTime.nextInt(60);

        this.time = String.format("%02d:%02d:%02d", hour, minute, second);
    }

    private void geraAddress() {
        String street = streets[geradorAddress.nextInt(streets.length)];
        String city = cities[geradorAddress.nextInt(cities.length)];
        String state = states[geradorAddress.nextInt(states.length)];
        String zipCode = zipCodes[geradorAddress.nextInt(zipCodes.length)];

        this.address = street + ", " + city + ", " + state + " " + zipCode;


    }

 
 

    private void geraSpeed() {
        this.speed = geradorSpeed.nextDouble() * maxSpeedGenerated; // Gera um valor entre 0 e maxSpeedGenerated
    }

    public double getSpeed() {
        return speed;
    }

    public String getAddress() {
        return address;
    }

    private void geraCarColor() {
        int randomIndex = geradorCarColor.nextInt(carColors.length);
        this.carColor = carColors[randomIndex];
    }

    private void geraCarBrand() {
        int randomIndex = geradorCarBrand.nextInt(carBrands.length);
        this.carBrand = carBrands[randomIndex];
    }

    private void geraCarType() {
        int randomIndex = geradorCarType.nextInt(carTypes.length);
        this.carType = carTypes[randomIndex];
    }

    private void geraVehicleOwnerName(){
        int randomIndex = geradorOwnerName.nextInt(ownerNames.length);
        this.vehicleOwnerName = ownerNames[randomIndex];
    }

    private void geraVehicleOwnerSurName(){
        int randomIndex = geradorOwnerSurName.nextInt(ownerSurNames.length);
        this.vehicleOwnerSurName = ownerSurNames[randomIndex];
    }

    private void geraFullName(){
        this.vehicleOwnerName = this.vehicleOwnerName + " " + this.vehicleOwnerSurName;
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

    private void geraMaxSpeed() {
        int randomIndex = geradorMaxSpeed.nextInt(maxSpeedsList.length);
        this.maxSpeed = maxSpeedsList[randomIndex];
    }

    private void geraDirection() {
        int randomIndex = geradorDirection.nextInt(directions.length);
        this.direction = directions[randomIndex];
    }

    private void geraStreetDirection() {
        int randomIndex = geradorStreetDirection.nextInt(streetDirections.length);
        this.streetDirection = streetDirections[randomIndex];
    }


    private void geraViolation() {
        // gera valor aleatorio de infração, para que nem todos os carros tenham infração
        double randomValue = geradorViolation.nextDouble();
    
      
        double desiredRate = 0.12;
    
   
        if (randomValue < desiredRate) {
            int randomIndex = geradorViolation.nextInt(violationList.length);
            this.violation = violationList[randomIndex];
        } else {
            this.violation = null; 
        }
    }

    public String getViolation() {
        return violation;
    }

    public String getDirection() {
        return direction;
    }

    public String getStreetDirection() {
        return streetDirection;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getDate() {
        return date;
    }

    public String getCarType() {
        return carType;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public String getCarColor() {
        return carColor;
    }

    public String getCarBrand() {
        return carBrand;
    }

   

    public  String getFullName(){
        geraFullName();
        return this.vehicleOwnerName;
    }

    public String getTime() {
        return time;
    }

    
    
    private double calculatePollutionLevel(String carType, String carBrand) {
        int maxTries = 3;  // Número máximo de tentativas
        int tries = 0;
    
        while (tries < maxTries) {
            try {
                if (carBrandTypePollution.containsKey(carBrand)) {
                    Map<String, Double> typePollution = carBrandTypePollution.get(carBrand);
                    if (typePollution.containsKey(carType)) {
                        double pollutionLevel = typePollution.get(carType);
    
                        
                        double randomVariation = (Math.random() - 0.5) * 0.1; // Variação entre -0.05 e 0.05
                        pollutionLevel += randomVariation;
    
                        return pollutionLevel;
                    } else {
                        throw new IllegalArgumentException("Car type '" + carType + "' not found for the brand '" + carBrand + "'.");
                    }
                } else {
                    throw new IllegalArgumentException("Car brand '" + carBrand + "' not found.");
                }
            } catch (Exception e) {
                // Ocorreu um erro, vamos tentar novamente
                tries++;
    
                if (tries >= maxTries) {
                    // Se atingiu o número máximo de tentativas e ainda há um erro, podemos lançar a exceção
                    throw e;
                }
            }
        }
    
        // Se chegou aqui, significa que todas as tentativas falharam
        throw new RuntimeException("Unable to calculate pollution level.");
    }
    
    
    public double getPollutionLevel() {
        return calculatePollutionLevel(this.carType, this.carBrand);
    }
    
   
}


