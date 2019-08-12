package com.airbnb.airrequest;

import p032rx.Observable;
import p032rx.functions.Func1;
import retrofit2.Response;
import retrofit2.Retrofit;

class FlatMapAirResponseOperator<T> implements Func1<Response<T>, Observable<AirResponse<T>>> {
    private final AirRequest airRequest;
    private final Retrofit retrofit;

    FlatMapAirResponseOperator(Retrofit retrofit3, AirRequest airRequest2) {
        this.retrofit = retrofit3;
        this.airRequest = airRequest2;
    }

    public Observable<AirResponse<T>> call(Response<T> response) {
        if (response.isSuccessful()) {
            return Observable.just(new AirResponse(this.airRequest, response));
        }
        return Observable.error(new AirRequestNetworkException(this.retrofit, response, this.airRequest));
    }
}
