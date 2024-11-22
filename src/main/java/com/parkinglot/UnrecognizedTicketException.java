package com.parkinglot;

public class UnrecognizedTicketException extends RuntimeException{
    private static final String UNRECOGNIZED_TICKET = "Unrecognized parking ticket.";

    public UnrecognizedTicketException() {
        super(UNRECOGNIZED_TICKET);
    }
}
