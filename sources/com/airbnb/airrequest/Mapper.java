package com.airbnb.airrequest;

import p032rx.Observable;
import p032rx.functions.Func1;
import p032rx.functions.Func2;
import retrofit2.Retrofit;

class Mapper<T> implements Func2<AirRequest, ObservableAirRequest, Observable<? extends AirResponse<T>>> {
    private final Retrofit retrofit;

    Mapper(Retrofit retrofit3) {
        this.retrofit = retrofit3;
    }

    public Observable<? extends AirResponse<T>> call(AirRequest request, ObservableAirRequest observableRequest) {
        return ((Observable) observableRequest.rawRequest().newCall()).flatMap(new FlatMapAirResponseOperator(this.retrofit, request)).onErrorResumeNext((Func1<? super Throwable, ? extends Observable<? extends T>>) new ErrorMappingOperator<Object,Object>(request));
    }
}
