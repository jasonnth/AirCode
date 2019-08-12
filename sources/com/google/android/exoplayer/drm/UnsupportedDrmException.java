package com.google.android.exoplayer.drm;

public final class UnsupportedDrmException extends Exception {
    public final int reason;

    public UnsupportedDrmException(int reason2) {
        this.reason = reason2;
    }

    public UnsupportedDrmException(int reason2, Exception cause) {
        super(cause);
        this.reason = reason2;
    }
}
