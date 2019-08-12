package com.airbnb.airrequest;

import p032rx.Observable;
import p032rx.functions.Func1;

final class TransformResponseOperator<T> implements Func1<AirResponse<T>, Observable<AirResponse<T>>> {
    private final AirRequest request;

    TransformResponseOperator(AirRequest request2) {
        this.request = request2;
    }

    public Observable<AirResponse<T>> call(AirResponse<T> airResponse) {
        if (!(this.request instanceof BaseRequest)) {
            return Observable.just(airResponse);
        }
        try {
            return Observable.just(((BaseRequest) this.request).transformResponse(airResponse));
        } catch (RuntimeException e) {
            return Observable.error(new AirRequestNetworkException(this.request, (Throwable) e));
        }
    }
}
