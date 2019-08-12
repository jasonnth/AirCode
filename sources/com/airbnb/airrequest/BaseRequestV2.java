package com.airbnb.airrequest;

import p032rx.Observer;

public abstract class BaseRequestV2<T> extends BaseRequest<T> {
    private static final String API_VERSION_V2 = "v2/";

    public String getPathPrefix() {
        return API_VERSION_V2;
    }

    public BaseRequestV2<T> withListener(Observer<AirResponse<T>> listener) {
        return (BaseRequestV2) super.withListener(listener);
    }
}
