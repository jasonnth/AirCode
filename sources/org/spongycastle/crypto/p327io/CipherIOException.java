package org.spongycastle.crypto.p327io;

import java.io.IOException;

/* renamed from: org.spongycastle.crypto.io.CipherIOException */
public class CipherIOException extends IOException {
    private static final long serialVersionUID = 1;
    private final Throwable cause;

    public CipherIOException(String message, Throwable cause2) {
        super(message);
        this.cause = cause2;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
