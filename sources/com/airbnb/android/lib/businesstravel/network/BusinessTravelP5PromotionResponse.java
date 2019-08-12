package com.airbnb.android.lib.businesstravel.network;

import com.airbnb.android.lib.businesstravel.models.BTMobileSignupPromotion;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessTravelP5PromotionResponse {
    @JsonProperty("business_travel_p5_customization")
    public BusinessTravelP5Customization businessTravelP5Customization;

    public static class BusinessTravelP5Customization {
        @JsonProperty("mobile_signup_promotion")
        public BTMobileSignupPromotion mobileSignupPromotion;
    }
}
