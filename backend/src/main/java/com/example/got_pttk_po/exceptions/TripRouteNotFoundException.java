package com.example.got_pttk_po.exceptions;

public class TripRouteNotFoundException extends RuntimeException {

    public TripRouteNotFoundException(Integer id) {
        super("Could not find trip route " + id);
    }
}