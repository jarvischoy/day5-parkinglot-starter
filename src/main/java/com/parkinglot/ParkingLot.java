package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String UNRECOGNIZED_TICKET = "Unrecognized parking ticket.";

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
        Car fetchedCar = parkingRecord.remove(ticket);
        if (fetchedCar == null) {
            System.out.println(UNRECOGNIZED_TICKET);
        }
        return fetchedCar;
    }
}
