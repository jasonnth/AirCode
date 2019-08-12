package com.airbnb.airrequest;

public class BatchException extends AirRequestNetworkException {
    public BatchException(AirRequest airRequest, Object errorResponse) {
        super(airRequest, errorResponse);
    }
}
