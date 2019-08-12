package com.braintreepayments.api.exceptions;

public class AuthorizationException extends Exception {
    public AuthorizationException(String message) {
        super(message);
    }
}
