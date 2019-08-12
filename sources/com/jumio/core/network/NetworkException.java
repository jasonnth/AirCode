package com.jumio.core.network;

public class NetworkException extends Exception {
    public NetworkException(Exception wrappedException) {
        super(wrappedException);
    }

    public NetworkException(String message) {
        super(message);
    }
}
