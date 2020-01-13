package com.example.got_pttk_po.exceptions;

public class TripCannotBeDeletedException  extends RuntimeException {

    public TripCannotBeDeletedException(Integer id) {
        super("Could not delete trip " + id);
    }
}