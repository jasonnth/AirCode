package com.airbnb.airrequest;

import com.airbnb.airrequest.BaseResponse.Metadata;
import p032rx.functions.Func1;

final class ResponseMetadataOperator implements Func1<AirResponse<?>, AirResponse<?>> {
    private final AirRequest airRequest;

    ResponseMetadataOperator(AirRequest airRequest2) {
        this.airRequest = airRequest2;
    }

    public AirResponse<?> call(AirResponse<?> airResponse) {
        if (airResponse.body() instanceof BaseResponse) {
            ((BaseResponse) airResponse.body()).metadata = new Metadata(this.airRequest, airResponse);
        }
        return airResponse;
    }
}
