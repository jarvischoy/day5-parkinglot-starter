package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingStrategy implements ParkingStrategy{
    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparingInt(ParkingLot::getAvailablePosition))
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
