package com.airbnb.airrequest;

import com.airbnb.airrequest.AirRequest.RequestType;

public abstract class FormUrlRequest<T> extends BaseRequest<T> {
    public abstract QueryStrap getFields();

    public RequestType getRequestType() {
        return RequestType.FORM_URL;
    }
}
