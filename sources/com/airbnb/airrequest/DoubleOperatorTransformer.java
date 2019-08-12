package com.airbnb.airrequest;

import p032rx.Observable;
import p032rx.Observable.Transformer;
import retrofit2.Retrofit;

class DoubleOperatorTransformer<T> implements Transformer<AirResponse<T>, AirResponse<T>> {
    private final Mapper mapper;
    private final ObservableAirRequest observableAirRequest;
    private final Retrofit retrofit;

    DoubleOperatorTransformer(Retrofit retrofit3, ObservableAirRequest observableAirRequest2, Mapper mapper2) {
        this.retrofit = retrofit3;
        this.observableAirRequest = observableAirRequest2;
        this.mapper = mapper2;
    }

    public Observable<AirResponse<T>> call(Observable<AirResponse<T>> observable) {
        return observable.flatMap(new DoubleMapOperator(this.retrofit, this.observableAirRequest, this.mapper));
    }
}
