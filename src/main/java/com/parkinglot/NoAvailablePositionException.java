package com.parkinglot;

public class NoAvailablePositionException extends RuntimeException {
    private static final String NO_AVAILABLE_POSITION = "No available position.";

    public NoAvailablePositionException() {
        super(NO_AVAILABLE_POSITION);
    }
}
