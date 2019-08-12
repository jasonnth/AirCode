package org.spongycastle.x509.util;

public class StreamParsingException extends Exception {

    /* renamed from: _e */
    Throwable f7245_e;

    public StreamParsingException(String message, Throwable e) {
        super(message);
        this.f7245_e = e;
    }

    public Throwable getCause() {
        return this.f7245_e;
    }
}
