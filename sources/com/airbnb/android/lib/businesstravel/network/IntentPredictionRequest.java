package com.airbnb.android.lib.businesstravel.network;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class IntentPredictionRequest extends BaseRequestV2<IntentPredictionResponse> {
    private static final String REQUEST_FORMAT = "for_mobile_p5";
    private final String confirmationCode;
    private final boolean shouldIncludeFalsePrediction;

    public static IntentPredictionRequest withFalsePrediction(String confirmationCode2, boolean shouldIncludeFalsePrediction2) {
        return new IntentPredictionRequest(confirmationCode2, shouldIncludeFalsePrediction2);
    }

    private IntentPredictionRequest(String confirmationCode2, boolean shouldIncludeFalsePrediction2) {
        this.confirmationCode = confirmationCode2;
        this.shouldIncludeFalsePrediction = shouldIncludeFalsePrediction2;
    }

    public Type successResponseType() {
        return IntentPredictionResponse.class;
    }

    public String getPath() {
        return "intent_predictions";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("confirmation_code", this.confirmationCode).mo7895kv(TimelineRequest.ARG_FORMAT, REQUEST_FORMAT).mo7897kv("include_false_predictions", this.shouldIncludeFalsePrediction);
    }
}
