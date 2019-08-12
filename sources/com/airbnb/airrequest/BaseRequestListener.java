package com.airbnb.airrequest;

import p032rx.Observer;
import p032rx.exceptions.OnErrorFailedException;

public abstract class BaseRequestListener<T> implements Observer<AirResponse<T>> {
    public abstract void onErrorResponse(AirRequestNetworkException airRequestNetworkException);

    public abstract void onResponse(T t);

    BaseRequestListener() {
    }

    public final void onError(Throwable e) {
        onErrorResponse((AirRequestNetworkException) e);
        onRequestCompleted(false);
    }

    public final void onNext(AirResponse<T> data) {
        Object obj;
        if (data != null) {
            try {
                obj = data.body();
            } catch (RuntimeException e) {
                throw new OnErrorFailedException(e);
            }
        } else {
            obj = null;
        }
        onResponse(obj);
    }

    public final void onCompleted() {
        try {
            onRequestCompleted(true);
        } catch (RuntimeException e) {
            throw new OnErrorFailedException(e);
        }
    }

    public void onRequestCompleted(boolean successful) {
    }
}
