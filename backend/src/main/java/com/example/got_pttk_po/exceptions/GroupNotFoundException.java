package com.example.got_pttk_po.exceptions;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(String id) { super("Could not find group " + id); }
}