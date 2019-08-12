package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewHostPromoResponse extends BaseResponse {
    @JsonProperty("new_hosting_promotion")
    NewHostPromo newHostPromo;

    private static class NewHostPromo {
        @JsonProperty("has_opted_in_new_hosting_promotion")
        Boolean isEnabled;

        private NewHostPromo() {
        }
    }

    public Boolean isEnabled() {
        return this.newHostPromo.isEnabled;
    }
}
