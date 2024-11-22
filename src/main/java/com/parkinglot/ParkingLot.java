package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Ticket> issuedTickets;

    public ParkingLot() {
        issuedTickets = new ArrayList<>();
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket(car);
        issuedTickets.add(ticket);
        return ticket;
    }
}
