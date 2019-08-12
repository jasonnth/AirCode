package com.airbnb.android.react;

import java.io.IOException;
import okhttp3.Callback;
import p032rx.Observer;

class DelegatingObserver implements Observer<CallAndResponse> {
    private final Callback delegate;

    DelegatingObserver(Callback delegate2) {
        this.delegate = delegate2;
    }

    public void onCompleted() {
    }

    public void onError(Throwable e) {
        this.delegate.onFailure(null, e instanceof IOException ? (IOException) e : new IOException(e));
    }

    public void onNext(CallAndResponse data) {
        try {
            this.delegate.onResponse(data.call, data.response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
