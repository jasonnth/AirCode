package com.facebook.react.bridge;

public class NativeArgumentsParseException extends JSApplicationCausedNativeException {
    public NativeArgumentsParseException(String detailMessage) {
        super(detailMessage);
    }

    public NativeArgumentsParseException(String detailMessage, Throwable t) {
        super(detailMessage, t);
    }
}
