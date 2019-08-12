package com.airbnb.airrequest;

import retrofit2.Response;
import retrofit2.Retrofit;

public class AirRequestNetworkException extends NetworkExceptionImpl {
    private final AirRequest request;

    AirRequestNetworkException(Retrofit retrofit, Response<?> response, AirRequest request2) {
        super(retrofit, response, request2.errorResponseType());
        this.request = request2;
    }

    AirRequestNetworkException(AirRequest request2, Throwable throwable) {
        super(throwable);
        this.request = request2;
    }

    AirRequestNetworkException(AirRequest request2, Object errorResponse) {
        super(errorResponse);
        this.request = request2;
    }

    public AirRequest request() {
        return this.request;
    }
}
