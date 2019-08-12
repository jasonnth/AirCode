package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SpokenLanguage;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class GetSpokenLanguagesResponse extends BaseResponse {
    @JsonProperty("languages")
    public ArrayList<SpokenLanguage> languages;
}
