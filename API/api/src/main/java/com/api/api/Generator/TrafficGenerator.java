package com.api.api.Generator;


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
    private String[] violationList = {"red light", "stop sign", "no seat belt", "no helmet",  "no license", "no insurance", "no registration", "drunk driving", "reckless driving", "hit and run", "tailgating", "jaywalking", "illegal turn", "parking violation","","","" }; // no plate 



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
    }

  

    private void geraCarPlate() {
      
        StringBuilder plate = new StringBuilder();

         double randomValue = geradorViolation.nextDouble();
    
      
        double desiredRate = 0.3;

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
    
      
        double desiredRate = 0.4;
    
   
        if (randomValue < desiredRate) {
            int randomIndex = geradorViolation.nextInt(violationList.length);
            this.violation = violationList[randomIndex];
        } else {
            this.violation = ""; 
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



   
}


