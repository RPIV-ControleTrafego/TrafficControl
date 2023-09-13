package com.api.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.api.api.Generator.TrafficGenerator;


class TrafficGeneratorTests {

	 @Test
    public void testGetMaxSpeed() {
        TrafficGenerator trafficGenerator = new TrafficGenerator();

        // Obtenha a velocidade máxima da instância gerada
        int maxSpeed = trafficGenerator.getMaxSpeed();

        // Verifique se a velocidade máxima está dentro da faixa esperada
        assertTrue(maxSpeed >= 60 && maxSpeed <= 260);
    }

	@Test
	public void testGetCarPlate() {
		TrafficGenerator trafficGenerator = new TrafficGenerator();

		// Obtenha a placa do carro da instância gerada
		String carPlate = trafficGenerator.getCarPlate();

		
		assertTrue(carPlate.matches("[A-Z]{3}-[0-9]{4}"));
	}

	@Test
	public void testGetCarType() {
		TrafficGenerator trafficGenerator = new TrafficGenerator();

		// Obtenha o tipo de carro da instância gerada
		String carType = trafficGenerator.getCarType();

		// Verifique se o tipo de carro está dentro da faixa esperada
		// "hatch", "sedan", "SUV", "crossover", "pickup"
		assertTrue(carType.matches("hatch|sedan|SUV|crossover|pickup"));
	}

	@Test
	public void testGetCarColor() {
		TrafficGenerator trafficGenerator = new TrafficGenerator();

		// Obtenha a cor do carro da instância gerada
		String carColor = trafficGenerator.getCarColor();

		//"red", "blue", "black", "white", "silver", "gray", "brown", "yellow", "green
		// Verifique se a cor do carro está dentro da faixa esperada
		assertTrue(carColor.matches("red|blue|black|white|silver|gray|brown|yellow|green"));
	}

	@Test
	public void testGetCarBrand() {
		TrafficGenerator trafficGenerator = new TrafficGenerator();

		// Obtenha a marca do carro da instância gerada
		String carBrand = trafficGenerator.getCarBrand();

		// Verifique se a marca do carro está dentro da faixa esperada
		// "Ford", "Toyota", "Chevrolet", "Peugeot", "Renault", "Mazda", "Lexus", "BYD", "Mitsubishi"
		assertTrue(carBrand.matches("Ford|Toyota|Chevrolet|Peugeot|Renault|Mazda|Lexus|BYD|Mitsubishi"));
	}

	@Test
	public void testGetVehicleOwnerName() {
		TrafficGenerator trafficGenerator = new TrafficGenerator();

		// Obtenha o nome do proprietário do veículo da instância gerada
		String vehicleOwnerName = trafficGenerator.getVehicleOwnerName();

		// Verifique se o nome do proprietário do veículo está dentro da faixa esperada
		// "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Helen", "Ivy", "Jack", "Liam", "Mia", "Noah", "Olivia", "Sophia"
		assertTrue(vehicleOwnerName.matches("Alice|Bob|Charlie|David|Eva|Frank|Grace|Helen|Ivy|Jack|Liam|Mia|Noah|Olivia|Sophia"));
	}

	@Test
	public void testGetVehicleOwnerSurName() {
		TrafficGenerator trafficGenerator = new TrafficGenerator();

		// Obtenha o sobrenome do proprietário do veículo da instância gerada
		String vehicleOwnerSurName = trafficGenerator.getVehicleOwnerSurName();

		// Verifique se o sobrenome do proprietário do veículo está dentro da faixa esperada
		// "Smith", "Johnson", "Brown", "Lee", "Wilson", "Davis", "Martinez", "Garcia", "Anderson", "Taylor", "Moore", "Jackson", "White", "Harris", "Clark"
		assertTrue(vehicleOwnerSurName.matches("Smith|Johnson|Brown|Lee|Wilson|Davis|Martinez|Garcia|Anderson|Taylor|Moore|Jackson|White|Harris|Clark"));
	}

	
	@Test
	public void testGetFullName(){
		TrafficGenerator trafficGenerator = new TrafficGenerator();

		// Obtenha o nome completo do proprietário do veículo da instância gerada
		String fullName = trafficGenerator.getFullName();

		// Verifique se o nome completo do proprietário do veículo está dentro da faixa esperada
		assertTrue(fullName.matches("[A-Z][a-z]+ [A-Z][a-z]+"));
	}

	@Test
    public void testGetAddress() {
        TrafficGenerator trafficGenerator = new TrafficGenerator();

        // Obtenha o endereço da instância gerada
        String address = trafficGenerator.getAddress();

        assertNotEquals(address, "Main Street, 10001, New York, NY");	
    }

	@Test
	public void testGetSpeed() {
		TrafficGenerator trafficGenerator = new TrafficGenerator();

		// Obtenha a velocidade da instância gerada
		double speed = trafficGenerator.getSpeed();

		// Verifique se a velocidade está dentro da faixa esperada
		assertTrue(speed >= 0 && speed <= 260);
	}

	@Test
	public void testGetDirection() {
		TrafficGenerator trafficGenerator = new TrafficGenerator();

		// Obtenha a direção da instância gerada
		String direction = trafficGenerator.getDirection();

		// Verifique se a direção está dentro da faixa esperada
		assertTrue(direction.matches("north|south|east|west"));
	}

	@Test
	public void testGetStreetDirection() {
		TrafficGenerator trafficGenerator = new TrafficGenerator();

		// Obtenha a direção da rua da instância gerada
		String streetDirection = trafficGenerator.getStreetDirection();

		// Verifique se a direção da rua está dentro da faixa esperada
		assertTrue(streetDirection.matches("north|south|east|west"));
	}
	



}
