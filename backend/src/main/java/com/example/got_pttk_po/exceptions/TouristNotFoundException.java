package com.example.got_pttk_po.exceptions;

public class TouristNotFoundException  extends RuntimeException {

    public TouristNotFoundException(String id) {
        super("Could not find tourist " + id);
    }
}