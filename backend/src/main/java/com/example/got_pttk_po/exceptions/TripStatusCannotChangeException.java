package com.example.got_pttk_po.exceptions;

public class TripStatusCannotChangeException  extends RuntimeException {

    public TripStatusCannotChangeException(Integer id) {
        super("Could not change status of trip " + id);
    }
}