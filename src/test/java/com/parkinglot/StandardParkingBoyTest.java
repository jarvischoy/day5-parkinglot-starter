package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {

    @Test
    void should_return_tickets_when_park_given_a_car_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();

        // When
        Ticket ticket = standardParkingBoy.park(car);

        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_valid_ticket_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = standardParkingBoy.park(car);

        // When
        Car fetchedCar = standardParkingBoy.fetch(ticket);

        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_correct_cars_when_fetch_given_multiple_valid_tickets_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = standardParkingBoy.park(car1);
        Ticket ticket2 = standardParkingBoy.park(car2);

        // When
        Car fetchedCar1 = standardParkingBoy.fetch(ticket1);
        Car fetchedCar2 = standardParkingBoy.fetch(ticket2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_throw_unrecognized_error_message_when_fetch_given_unrecognized_ticket_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = new Ticket();

        // When
        try {
            Car fetchedCar = standardParkingBoy.fetch(ticket);
        } catch (Exception e) {
            // Then
            assertEquals(UnrecognizedTicketException.class, e.getClass());
            assertTrue(e.getMessage().contains("Unrecognized parking ticket."));
        }
    }

    @Test
    void should_throw_unrecognized_error_message_when_fetch_given_used_ticket_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(ticket);

        // When
        try {
            Car fetchedCar = standardParkingBoy.fetch(ticket);
        } catch (Exception e) {
            // Then
            assertEquals(UnrecognizedTicketException.class, e.getClass());
            assertTrue(e.getMessage().contains("Unrecognized parking ticket."));
        }
    }

    @Test
    void should_throw_no_available_position_error_message_when_park_given_parkingLot_is_full_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        standardParkingBoy.park(car);

        Car carToPark = new Car();

        // When
        try {
            Ticket ticket = standardParkingBoy.park(carToPark);
        } catch (Exception e) {
            // Then
            assertEquals(NoAvailablePositionException.class, e.getClass());
            assertTrue(e.getMessage().contains("No available position."));
        }
    }

    @Test
    void should_return_ticket_when_park_given_two_parking_lots_and_a_car() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot1);
        standardParkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();

        // When
        Ticket ticket = standardParkingBoy.park(car);

        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_ticket_of_second_parking_lot_when_park_given_two_parking_lots_and_a_car_where_the_first_parking_lot_is_full() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot1);
        standardParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        standardParkingBoy.park(car1);

        Car car2 = new Car();

        // When
        Ticket ticket = standardParkingBoy.park(car2);

        // Then
        assertNotNull(ticket);
        assertEquals(parkingLot2.getId(), ticket.getParkingLotId());
    }

    @Test
    void should_return_the_correct_car_when_fetch_twice_given_two_parking_lots_and_two_cars_and_two_valid_tickets_where_both_parking_lots_each_has_one_car_parked() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot1);
        standardParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticketForParkingLot1 = standardParkingBoy.park(car1);
        Ticket ticketForParkingLot2 = standardParkingBoy.park(car2);

        // When
        Car fetchedCar1 = standardParkingBoy.fetch(ticketForParkingLot1);
        Car fetchedCar2 = standardParkingBoy.fetch(ticketForParkingLot2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_throws_unrecognized_error_message_when_fetch_given_two_parking_lots_and_a_car_and_a_unrecognized_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot1);
        standardParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        standardParkingBoy.park(car1);

        Ticket unrecognizedTicket = new Ticket();

        // When
        try {
            Car fetchedCar = standardParkingBoy.fetch(unrecognizedTicket);
        } catch (Exception e) {
            // Then
            assertEquals(UnrecognizedTicketException.class, e.getClass());
            assertTrue(e.getMessage().contains("Unrecognized parking ticket."));
        }
    }

    @Test
    void should_throws_unrecognized_error_message_when_fetch_given_two_parking_lots_and_a_car_and_a_used_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot1);
        standardParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        Ticket ticket = standardParkingBoy.park(car1);
        Car car = standardParkingBoy.fetch(ticket);

        // When
        try {
            Car fetchedCar = standardParkingBoy.fetch(ticket);
        } catch (Exception e) {
            // Then
            assertEquals(UnrecognizedTicketException.class, e.getClass());
            assertTrue(e.getMessage().contains("Unrecognized parking ticket."));
        }
    }

    @Test
    void should_throws_no_available_position_error_message_when_park_given_two_parking_lots_and_a_car_where_both_parking_lots_is_full() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingBoy standardParkingBoy = new ParkingBoy();
        standardParkingBoy.addParkingLot(parkingLot1);
        standardParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        standardParkingBoy.park(car1);
        standardParkingBoy.park(car2);

        Car carToPark = new Car();

        // When
        try {
            standardParkingBoy.park(carToPark);
        } catch (Exception e) {
            // Then
            assertEquals(NoAvailablePositionException.class, e.getClass());
            assertTrue(e.getMessage().contains("No available position."));
        }
    }
}
