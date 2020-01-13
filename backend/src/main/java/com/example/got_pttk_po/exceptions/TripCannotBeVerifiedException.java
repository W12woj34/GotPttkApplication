package com.example.got_pttk_po.exceptions;

public class TripCannotBeVerifiedException  extends RuntimeException {

    public TripCannotBeVerifiedException(Integer id) {
        super("Could not verify trip " + id);
    }
}