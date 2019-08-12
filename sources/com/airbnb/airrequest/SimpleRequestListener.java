package com.airbnb.airrequest;

public abstract class SimpleRequestListener<T> extends NonResubscribableRequestListener<T> {
    protected SimpleRequestListener() {
    }

    public void onResponse(T t) {
    }

    public void onErrorResponse(AirRequestNetworkException e) {
    }
}
