package com.example.got_pttk_po.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super("Could not find user " + id);
    }
}