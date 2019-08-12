package com.airbnb.airrequest;

import android.app.ActivityManager;
import p032rx.Observable;
import retrofit2.Retrofit;

final class AirRequestMapperImpl implements AirRequestMapper {
    private final Mapper<?> mapper;
    private final ObservableAirRequestFactory observableAirRequestFactory;
    private final Retrofit retrofit;

    AirRequestMapperImpl(Retrofit retrofit3, ObservableAirRequestFactory observableAirRequestFactory2, Mapper<?> mapper2) {
        this.retrofit = retrofit3;
        this.observableAirRequestFactory = observableAirRequestFactory2;
        this.mapper = mapper2;
    }

    public Observable<? extends AirResponse<?>> call(AirRequest airRequest) {
        if (!ActivityManager.isUserAMonkey() || airRequest.isAllowedIfMonkey()) {
            return toObservable(airRequest).map(new ResponseMetadataOperator(airRequest)).flatMap(new TransformResponseOperator(airRequest));
        }
        return Observable.empty();
    }

    private <T> Observable<AirResponse<T>> toObservable(AirRequest airRequest) {
        if (airRequest.isSkipCache() && airRequest.isDoubleResponse()) {
            throw new IllegalStateException("Skip cache is not valid for double responses.");
        } else if (!airRequest.isDoubleResponse() || airRequest.getMethod() == RequestMethod.GET) {
            ObservableAirRequest observableAirRequest = this.observableAirRequestFactory.newObservableRequest(airRequest);
            return this.mapper.call(airRequest, observableAirRequest).compose(new DoubleOperatorTransformer(this.retrofit, observableAirRequest, this.mapper));
        } else {
            throw new IllegalStateException("Double response can only be used with GET requests");
        }
    }
}
