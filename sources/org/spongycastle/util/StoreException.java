package org.spongycastle.util;

public class StoreException extends RuntimeException {

    /* renamed from: _e */
    private Throwable f7242_e;

    public StoreException(String msg, Throwable cause) {
        super(msg);
        this.f7242_e = cause;
    }

    public Throwable getCause() {
        return this.f7242_e;
    }
}
