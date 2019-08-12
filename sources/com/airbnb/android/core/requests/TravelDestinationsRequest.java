package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.TravelDestinationsResponse;
import java.lang.reflect.Type;
import p032rx.Observer;

public class TravelDestinationsRequest extends BaseRequestV2<TravelDestinationsResponse> {
    public TravelDestinationsRequest() {
    }

    @Deprecated
    public TravelDestinationsRequest(BaseRequestListener<TravelDestinationsResponse> listener) {
        withListener((Observer) listener);
    }

    public String getPath() {
        return "travel_destinations";
    }

    public long getCacheOnlyTimeoutMs() {
        return 86400000;
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public Type successResponseType() {
        return TravelDestinationsResponse.class;
    }
}
