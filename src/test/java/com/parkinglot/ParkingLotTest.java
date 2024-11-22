package com.parkinglot;


import org.junit.jupiter.api.Test;

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


}
