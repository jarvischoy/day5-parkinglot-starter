package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Car car = new Car();

        // When
        Ticket ticket = parkingLot.park(car);

        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_valid_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
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
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        // When
        Car fetchedCar1 = parkingLot.fetch(ticket1);
        Car fetchedCar2 = parkingLot.fetch(ticket2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_throw_unrecognized_error_message_when_fetch_given_unrecognized_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = new Ticket();

        // When
        try {
            Car fetchedCar = parkingLot.fetch(ticket);
        } catch (Exception e) {
            // Then
            assertEquals(e.getClass(), UnrecognizedTicketException.class);
            assertTrue(e.getMessage().contains("Unrecognized parking ticket."));
        }
    }

    @Test
    void should_throw_unrecognized_error_message_when_fetch_given_used_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);

        // When
        try {
            Car fetchedCar = parkingLot.fetch(ticket);
        } catch (Exception e) {
            // Then
            assertEquals(e.getClass(), UnrecognizedTicketException.class);
            assertTrue(e.getMessage().contains("Unrecognized parking ticket."));
        }
    }

    @Test
    void should_throw_no_available_position_error_message_when_park_given_parkingLot_is_full() {
        // Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);

        Car carToPark = new Car();

        // When
        try {
            Ticket ticket = parkingLot.park(carToPark);
        } catch (Exception e) {
            // Then
            assertEquals(e.getClass(), NoAvailablePositionException.class);
            assertTrue(e.getMessage().contains("No available position."));
        }
    }
}
