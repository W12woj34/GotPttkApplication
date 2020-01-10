package com.example.got_pttk_po.exceptions;

public class PodgrupaNotFoundException extends RuntimeException {

    public PodgrupaNotFoundException(Integer id) {
        super("Could not find podgrupa " + id);
    }
}