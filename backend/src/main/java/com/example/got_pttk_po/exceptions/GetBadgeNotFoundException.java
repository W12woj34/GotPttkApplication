package com.example.got_pttk_po.exceptions;

public class GetBadgeNotFoundException extends RuntimeException {

    public GetBadgeNotFoundException(Integer id) { super("Could not find badge " + id); }
}