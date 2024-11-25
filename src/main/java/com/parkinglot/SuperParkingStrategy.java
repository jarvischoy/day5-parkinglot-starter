package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingStrategy implements ParkingStrategy{
    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate))
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
