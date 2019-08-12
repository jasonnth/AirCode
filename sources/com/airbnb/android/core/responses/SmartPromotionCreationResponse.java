package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CalendarDayPromotion;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SmartPromotionCreationResponse extends BaseResponse {
    @JsonProperty("smart_promotion_host_promotion")
    CalendarDayPromotion smartPromotion;

    public CalendarDayPromotion getSmartPromotion() {
        return this.smartPromotion;
    }
}
