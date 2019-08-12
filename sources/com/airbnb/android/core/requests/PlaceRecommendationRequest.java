package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.PlaceRecommendationResponse;
import java.lang.reflect.Type;
import p032rx.Observer;

public class PlaceRecommendationRequest extends BaseRequestV2<PlaceRecommendationResponse> {
    private final long placeRecommendationId;

    public PlaceRecommendationRequest(long placeRecommendationId2, BaseRequestListener<PlaceRecommendationResponse> listener) {
        withListener((Observer) listener);
        this.placeRecommendationId = placeRecommendationId2;
    }

    public String getPath() {
        return "place_recommendations/" + this.placeRecommendationId;
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public Type successResponseType() {
        return PlaceRecommendationResponse.class;
    }
}
