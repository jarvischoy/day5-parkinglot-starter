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
    


}
