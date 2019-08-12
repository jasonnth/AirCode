package com.airbnb.airrequest;

public abstract class NonResubscribableRequestListener<T> extends BaseRequestListener<T> {
    public abstract void onErrorResponse(AirRequestNetworkException airRequestNetworkException);

    public abstract void onResponse(T t);
}
