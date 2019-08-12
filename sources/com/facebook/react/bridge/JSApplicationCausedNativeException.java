package com.facebook.react.bridge;

public class JSApplicationCausedNativeException extends RuntimeException {
    public JSApplicationCausedNativeException(String detailMessage) {
        super(detailMessage);
    }

    public JSApplicationCausedNativeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
