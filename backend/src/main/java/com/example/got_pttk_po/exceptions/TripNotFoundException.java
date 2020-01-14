package com.example.got_pttk_po.exceptions;

public class TripNotFoundException extends RuntimeException {

    public TripNotFoundException(Integer id) {
        super("Could not find trip " + id);
    }
}