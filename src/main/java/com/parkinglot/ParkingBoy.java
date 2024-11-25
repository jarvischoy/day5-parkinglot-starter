package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    private final ParkingStrategy parkingStrategy;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
        this.parkingStrategy = new StandardParkingStrategy();
    }

    public ParkingBoy(ParkingStrategy parkingStrategy) {
        this.parkingLots = new ArrayList<>();
        this.parkingStrategy = parkingStrategy;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public Ticket park(Car car) {
        return parkingStrategy.park(parkingLots, car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.fetch(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new);
    }
}
