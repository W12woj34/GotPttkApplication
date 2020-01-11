package com.example.got_pttk_po.exceptions;

public class BadgeNotFoundException extends RuntimeException {

    public BadgeNotFoundException(String id) { super("Could not find badge " + id); }
}