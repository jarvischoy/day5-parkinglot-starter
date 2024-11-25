package com.parkinglot;

import java.util.List;

public interface ParkingStrategy {
    Ticket park(List<ParkingLot> parkingLots, Car car);
}
