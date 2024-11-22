package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    @Test
    void should_return_tickets_when_park_given_a_car_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        // When
        Ticket ticket = parkingBoy.park(car);

        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_valid_ticket_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);

        // When
        Car fetchedCar = parkingBoy.fetch(ticket);

        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_correct_cars_when_fetch_given_multiple_valid_tickets_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);

        // When
        Car fetchedCar1 = parkingBoy.fetch(ticket1);
        Car fetchedCar2 = parkingBoy.fetch(ticket2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_throw_unrecognized_error_message_when_fetch_given_unrecognized_ticket_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = new Ticket();

        // When
        try {
            Car fetchedCar = parkingBoy.fetch(ticket);
        } catch (Exception e) {
            // Then
            assertEquals(e.getClass(), UnrecognizedTicketException.class);
            assertTrue(e.getMessage().contains("Unrecognized parking ticket."));
        }
    }

    @Test
    void should_throw_unrecognized_error_message_when_fetch_given_used_ticket_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        // When
        try {
            Car fetchedCar = parkingBoy.fetch(ticket);
        } catch (Exception e) {
            // Then
            assertEquals(e.getClass(), UnrecognizedTicketException.class);
            assertTrue(e.getMessage().contains("Unrecognized parking ticket."));
        }
    }

    @Test
    void should_throw_no_available_position_error_message_when_park_given_parkingLot_is_full_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        parkingBoy.park(car);

        Car carToPark = new Car();

        // When
        try {
            Ticket ticket = parkingBoy.park(carToPark);
        } catch (Exception e) {
            // Then
            assertEquals(e.getClass(), NoAvailablePositionException.class);
            assertTrue(e.getMessage().contains("No available position."));
        }
    }
}
