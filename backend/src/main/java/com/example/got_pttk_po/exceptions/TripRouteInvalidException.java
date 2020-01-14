package com.example.got_pttk_po.exceptions;

public class TripRouteInvalidException extends RuntimeException {

    public TripRouteInvalidException(Integer id) {
        super("Could not add or modify trip route " + id);
    }
}