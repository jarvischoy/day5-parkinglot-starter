package com.parkinglot;

import java.util.Comparator;
import java.util.Objects;

public class SmartParkingBoy extends ParkingBoy {

    public Ticket park(Car car) {
        // park into the parking lot with the most available positions
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparingInt(ParkingLot::getAvailablePosition))
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.fetch(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new);
    }
}
