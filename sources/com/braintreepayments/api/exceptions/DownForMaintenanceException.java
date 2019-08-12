package com.braintreepayments.api.exceptions;

public class DownForMaintenanceException extends Exception {
    public DownForMaintenanceException(String message) {
        super(message);
    }
}
