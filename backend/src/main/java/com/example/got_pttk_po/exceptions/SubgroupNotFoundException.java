package com.example.got_pttk_po.exceptions;

public class SubgroupNotFoundException  extends RuntimeException {

    public SubgroupNotFoundException(String id) { super("Could not find subgroup " + id); }
}