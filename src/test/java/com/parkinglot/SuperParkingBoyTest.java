package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuperParkingBoyTest {

    @Test
    void should_return_tickets_when_park_given_a_car_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();

        // When
        Ticket ticket = superParkingBoy.park(car);

        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_valid_ticket_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = superParkingBoy.park(car);

        // When
        Car fetchedCar = superParkingBoy.fetch(ticket);

        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_correct_cars_when_fetch_given_multiple_valid_tickets_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = superParkingBoy.park(car1);
        Ticket ticket2 = superParkingBoy.park(car2);

        // When
        Car fetchedCar1 = superParkingBoy.fetch(ticket1);
        Car fetchedCar2 = superParkingBoy.fetch(ticket2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_throw_unrecognized_error_message_when_fetch_given_unrecognized_ticket_and_parkingLot() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = new Ticket();

        // When
        try {
            Car fetchedCar = superParkingBoy.fetch(ticket);
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
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = superParkingBoy.park(car);
        superParkingBoy.fetch(ticket);

        // When
        try {
            Car fetchedCar = superParkingBoy.fetch(ticket);
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
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot);
        Car car = new Car();
        superParkingBoy.park(car);

        Car carToPark = new Car();

        // When
        try {
            Ticket ticket = superParkingBoy.park(carToPark);
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
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Car car = new Car();

        // When
        Ticket ticket = superParkingBoy.park(car);

        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_ticket_of_second_parking_lot_when_park_given_two_parking_lots_and_a_car_where_the_first_parking_lot_is_full() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        superParkingBoy.park(car1);

        Car car2 = new Car();

        // When
        Ticket ticket = superParkingBoy.park(car2);

        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_correct_car_when_fetch_twice_given_two_parking_lots_and_two_cars_and_two_valid_tickets_where_both_parking_lots_each_has_one_car_parked() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticketForParkingLot1 = superParkingBoy.park(car1);
        Ticket ticketForParkingLot2 = superParkingBoy.park(car2);

        // When
        Car fetchedCar1 = superParkingBoy.fetch(ticketForParkingLot1);
        Car fetchedCar2 = superParkingBoy.fetch(ticketForParkingLot2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_throws_unrecognized_error_message_when_fetch_given_two_parking_lots_and_a_car_and_a_unrecognized_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        superParkingBoy.park(car1);

        Ticket unrecognizedTicket = new Ticket();

        // When
        try {
            Car fetchedCar = superParkingBoy.fetch(unrecognizedTicket);
        } catch (Exception e) {
            // Then
            assertEquals(UnrecognizedTicketException.class, e.getClass());
            assertTrue(e.getMessage().contains("Unrecognized parking ticket."));
        }
    }

    @Test
    void should_throws_unrecognized_error_message_when_fetch_given_two_parking_lots_and_a_car_and_a_used_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        Ticket ticket = superParkingBoy.park(car1);
        Car car = superParkingBoy.fetch(ticket);

        // When
        try {
            Car fetchedCar = superParkingBoy.fetch(ticket);
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
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(parkingLot1);
        superParkingBoy.addParkingLot(parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        superParkingBoy.park(car1);
        superParkingBoy.park(car2);

        Car carToPark = new Car();

        // When
        try {
            superParkingBoy.park(carToPark);
        } catch (Exception e) {
            // Then
            assertEquals(NoAvailablePositionException.class, e.getClass());
            assertTrue(e.getMessage().contains("No available position."));
        }
    }

    @Test
    void should_return_corresponding_car_when_fetch_given_two_parking_lots_and_five_tickets_and_five_cars() {
        //given
        ParkingBoy superParkingBoy = new ParkingBoy(new SuperParkingStrategy());
        superParkingBoy.addParkingLot(new ParkingLot(4));
        superParkingBoy.addParkingLot(new ParkingLot(8));
        Car car1 = new Car();
        Ticket parkingTicket1 = superParkingBoy.park(car1);

        Car car2 = new Car();
        Ticket parkingTicket2 = superParkingBoy.park(car2);

        Car car3 = new Car();
        Ticket parkingTicket3 = superParkingBoy.park(car3);

        Car car4 = new Car();
        Ticket parkingTicket4 = superParkingBoy.park(car4);

        Car car5 = new Car();
        Ticket parkingTicket5 = superParkingBoy.park(car5);

        assertEquals(parkingTicket1.getParkingLotId(), parkingTicket1.getParkingLotId());
        assertEquals(parkingTicket1.getParkingLotId(), parkingTicket2.getParkingLotId());
        assertEquals(parkingTicket1.getParkingLotId(), parkingTicket3.getParkingLotId());
        assertEquals(parkingTicket1.getParkingLotId(), parkingTicket4.getParkingLotId());
        assertEquals(parkingTicket1.getParkingLotId(), parkingTicket5.getParkingLotId());

        //when
        Car actualCar1 = superParkingBoy.fetch(parkingTicket1);
        Car actualCar2 = superParkingBoy.fetch(parkingTicket2);
        Car actualCar3 = superParkingBoy.fetch(parkingTicket3);
        Car actualCar4 = superParkingBoy.fetch(parkingTicket4);
        Car actualCar5 = superParkingBoy.fetch(parkingTicket5);

        //then
        assertEquals(car1, actualCar1);
        assertEquals(car2, actualCar2);
        assertEquals(car3, actualCar3);
        assertEquals(car4, actualCar4);
        assertEquals(car5, actualCar5);
    }


}
