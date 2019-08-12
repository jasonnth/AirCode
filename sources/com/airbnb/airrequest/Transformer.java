package com.airbnb.airrequest;

public interface Transformer<T> extends p032rx.Observable.Transformer<AirResponse<T>, AirResponse<T>> {

    public interface Factory {
        Transformer<?> transformerFor(AirRequest airRequest, AirRequestInitializer airRequestInitializer);
    }
}
