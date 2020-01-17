package com.example.got_pttk_po.exceptions;

public class TripRouteInvalidException extends RuntimeException {

    public TripRouteInvalidException(Integer id) {
        super("Could not add, modify or delete trip route " + id);
    }
}