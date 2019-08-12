package com.airbnb.airrequest;

import java.util.HashMap;
import java.util.Map;
import okhttp3.CacheControl;
import p032rx.Observable;
import p032rx.functions.Func1;
import retrofit2.ObservableRequest.Builder;
import retrofit2.Retrofit;

class DoubleMapOperator<T> implements Func1<AirResponse<T>, Observable<? extends AirResponse<T>>> {
    private final Mapper<?> mapper;
    private final ObservableAirRequest observableAirRequest;
    private final Retrofit retrofit;

    DoubleMapOperator(Retrofit retrofit3, ObservableAirRequest observableAirRequest2, Mapper<?> mapper2) {
        this.retrofit = retrofit3;
        this.observableAirRequest = observableAirRequest2;
        this.mapper = mapper2;
    }

    public Observable<? extends AirResponse<T>> call(AirResponse<T> response) {
        if (isEligibleForDouble(this.observableAirRequest.airRequest(), response)) {
            return Observable.concat(Observable.just(response), newDoubleObservable());
        }
        return Observable.just(response);
    }

    private boolean isEligibleForDouble(AirRequest immutableRequest, AirResponse<?> response) {
        return immutableRequest.isDoubleResponse() && immutableRequest.getMethod() == RequestMethod.GET && response.raw().networkResponse() == null && response.isSuccess();
    }

    private Observable<? extends AirResponse<T>> newDoubleObservable() {
        Builder builder = this.observableAirRequest.rawRequest().newBuilder(this.retrofit);
        Map<String, String> originalHeaders = this.observableAirRequest.rawRequest().headers();
        Map<String, String> headers = new HashMap<>(originalHeaders.size() + 1);
        headers.putAll(originalHeaders);
        headers.put("Cache-Control", CacheControl.FORCE_NETWORK.toString());
        return this.mapper.call(this.observableAirRequest.airRequest(), new ObservableAirRequest(this.observableAirRequest.airRequest(), builder.headers(headers).build())).onErrorResumeNext(Observable.empty());
    }
}
