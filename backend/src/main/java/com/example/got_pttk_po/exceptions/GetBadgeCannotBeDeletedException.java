package com.example.got_pttk_po.exceptions;

public class GetBadgeCannotBeDeletedException  extends RuntimeException {

    public GetBadgeCannotBeDeletedException(Integer id) {
        super("Could not delete badge " + id);
    }
}