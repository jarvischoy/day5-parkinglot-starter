package com.parkinglot;

import java.util.List;

public class StandardParkingStrategy implements ParkingStrategy {
    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
