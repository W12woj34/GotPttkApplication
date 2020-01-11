package com.example.got_pttk_po.exceptions;

public class BadgeNotPossibleException extends RuntimeException {

    public BadgeNotPossibleException(String id) { super("Could not add badge " + id); }
}