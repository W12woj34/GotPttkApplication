package com.example.got_pttk_po.exceptions;

public class SubgroupNotFoundException  extends RuntimeException {

    public SubgroupNotFoundException(Integer id) { super("Could not find badge " + id); }
}