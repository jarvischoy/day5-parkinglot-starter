package com.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SuperParkingBoy {
    private List<ParkingLot> parkingLots;

    public SuperParkingBoy() {
        this.parkingLots = new ArrayList<>();
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) {
        // park into the parking lot with the most rate of available positions
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate))
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
