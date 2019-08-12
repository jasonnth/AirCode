package org.spongycastle.util.p333io.pem;

import java.io.IOException;

/* renamed from: org.spongycastle.util.io.pem.PemGenerationException */
public class PemGenerationException extends IOException {
    private Throwable cause;

    public PemGenerationException(String message, Throwable cause2) {
        super(message);
        this.cause = cause2;
    }

    public PemGenerationException(String message) {
        super(message);
    }

    public Throwable getCause() {
        return this.cause;
    }
}
