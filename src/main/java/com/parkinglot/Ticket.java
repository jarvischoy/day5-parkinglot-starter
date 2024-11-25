package com.parkinglot;

public class Ticket {
    private final int parkingLotId;

    public Ticket() {
        parkingLotId = 1;
    }

    public Ticket(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }
}
