package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingPersonaInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ListingPersonaResponse extends BaseResponse {
    @JsonProperty("listing_persona_response")
    public ListingPersonaInput listingPersonaInput;
    @JsonProperty("listing_persona_responses")
    public List<ListingPersonaInput> listingPersonaInputs;
}
