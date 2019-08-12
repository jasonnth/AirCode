package com.airbnb.android.lib.businesstravel.network;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.lib.businesstravel.models.IntentPrediction;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class IntentPredictionResponse extends BaseResponse {
    @JsonProperty("intent_predictions")
    public List<IntentPrediction> intentPredictions;
}
