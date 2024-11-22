package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;

    private final int capacity;
    private final Map<Ticket, Car> parkingRecord;

    public ParkingLot() {
        parkingRecord = new HashMap<>();
        capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        parkingRecord = new HashMap<>();
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (parkingRecord.size() >= capacity) {
            return null;
        }
        Ticket ticket = new Ticket();
        parkingRecord.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return parkingRecord.remove(ticket);
    }
}
