package com.example.got_pttk_po.exceptions;

public class GetBadgeIsVerificatedException extends RuntimeException {

    public GetBadgeIsVerificatedException(Integer id) { super("Could not delete verified badge " + id); }
}