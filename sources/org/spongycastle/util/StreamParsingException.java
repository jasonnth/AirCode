package org.spongycastle.util;

public class StreamParsingException extends Exception {

    /* renamed from: _e */
    Throwable f7243_e;

    public StreamParsingException(String message, Throwable e) {
        super(message);
        this.f7243_e = e;
    }

    public Throwable getCause() {
        return this.f7243_e;
    }
}
