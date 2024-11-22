package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingBoy {
    protected List<ParkingLot> parkingLots;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public abstract Ticket park(Car car);

    public abstract Car fetch(Ticket ticket);
}
