package com.airbnb.android.core;

public class UnhandledStateException extends RuntimeException {
    public UnhandledStateException(Enum<?> e) {
        super(e.toString() + " was unhandled");
    }
}
