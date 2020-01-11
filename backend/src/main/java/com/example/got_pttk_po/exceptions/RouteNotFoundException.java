package com.example.got_pttk_po.exceptions;

public class RouteNotFoundException extends RuntimeException {

    public RouteNotFoundException(Integer id) { super("Could not find route " + id); }
}