package com.jumio.core.exceptions;

public class PlatformNotSupportedException extends Exception {
    private static final long serialVersionUID = 656729418726763588L;

    public PlatformNotSupportedException() {
    }

    public PlatformNotSupportedException(String detailMessage) {
        super(detailMessage);
    }

    public PlatformNotSupportedException(Throwable throwable) {
        super(throwable);
    }

    public PlatformNotSupportedException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
