package com.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<Ticket, Car> parkingRecord;

    public ParkingLot() {
        parkingRecord = new HashMap<>();
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        parkingRecord.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return parkingRecord.remove(ticket);
    }
}
