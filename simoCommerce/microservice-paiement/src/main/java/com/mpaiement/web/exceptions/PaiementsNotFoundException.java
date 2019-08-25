package com.mpaiement.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaiementsNotFoundException extends Throwable {
    public PaiementsNotFoundException(String s) {
    }
}
