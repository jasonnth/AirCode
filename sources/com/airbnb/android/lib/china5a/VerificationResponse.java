package com.airbnb.android.lib.china5a;

public class VerificationResponse<T> {
    public final T data;
    public final Throwable error;

    public VerificationResponse(T data2, Throwable error2) {
        this.data = data2;
        this.error = error2;
    }
}
