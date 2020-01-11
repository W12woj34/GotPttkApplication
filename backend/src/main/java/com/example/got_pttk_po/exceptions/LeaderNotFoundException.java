package com.example.got_pttk_po.exceptions;

public class LeaderNotFoundException extends RuntimeException {

    public LeaderNotFoundException(String id) {
        super("Could not find leader " + id);
    }
}