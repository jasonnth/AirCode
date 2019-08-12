package com.jumio.analytics;

public class DispatchException extends Exception {
    public DispatchException(String s) {
        super(s);
    }

    public DispatchException() {
    }

    public DispatchException(Exception other) {
        super(other);
    }
}
