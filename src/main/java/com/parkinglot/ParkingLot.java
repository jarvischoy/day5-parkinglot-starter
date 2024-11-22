package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;

    // use clock to generate id
    private int id = (int) System.currentTimeMillis();
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
        Ticket ticket = new Ticket(id);
        parkingRecord.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return parkingRecord.remove(ticket);
    }

    public boolean isFull() {
        return parkingRecord.size() >= capacity;
    }

    public int getAvailablePosition() {
        return capacity - parkingRecord.size();
    }

    public double getAvailablePositionRate() {
        return (double) getAvailablePosition() / capacity;
    }

    public int getId() {
        return id;
    }
}
