package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {

    @Test
    public void testInitParkingLotNotNull() {
        ParkingLot parkingLot = new ParkingLot();
        assertNotNull(parkingLot);
    }

    @Test
    void should_return_tickets_when_park_given_a_car() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car(1);

        // When
        Ticket ticket = parkingLot.park(car);

        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_valid_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car(1);
        Ticket ticket = parkingLot.park(car);

        // When
        Car fetchedCar = parkingLot.fetch(ticket);

        // Then
        assertEquals(car, fetchedCar);
    }
    
    @Test
    void should_return_correct_cars_when_fetch_given_multiple_valid_tickets() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        // When
        Car fetchedCar1 = parkingLot.fetch(ticket1);
        Car fetchedCar2 = parkingLot.fetch(ticket2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }


}
