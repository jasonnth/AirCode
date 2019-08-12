package com.braintreepayments.api.exceptions;

public class ConfigurationException extends BraintreeException {
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationException(String message) {
        super(message);
    }
}
