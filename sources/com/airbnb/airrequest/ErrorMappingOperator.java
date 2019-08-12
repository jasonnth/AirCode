package com.airbnb.airrequest;

import p032rx.Observable;
import p032rx.functions.Func1;

class ErrorMappingOperator<T> implements Func1<Throwable, Observable<AirResponse<T>>> {
    private final AirRequest airRequest;

    ErrorMappingOperator(AirRequest airRequest2) {
        this.airRequest = airRequest2;
    }

    public Observable<AirResponse<T>> call(Throwable throwable) {
        if (throwable instanceof NetworkException) {
            return Observable.error(throwable);
        }
        return Observable.error(new AirRequestNetworkException(this.airRequest, throwable));
    }
}
