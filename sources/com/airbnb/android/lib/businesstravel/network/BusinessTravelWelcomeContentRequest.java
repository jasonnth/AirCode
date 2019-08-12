package com.airbnb.android.lib.businesstravel.network;

import com.airbnb.airrequest.BaseRequestV2;
import java.lang.reflect.Type;

public class BusinessTravelWelcomeContentRequest extends BaseRequestV2<BusinessTravelWelcomeContentResponse> {
    private static final String BASE_PATH = "airbnb_for_work_signup_landing_contents/";
    private final long userId;

    public static BusinessTravelWelcomeContentRequest forUserId(long userId2) {
        return new BusinessTravelWelcomeContentRequest(userId2);
    }

    private BusinessTravelWelcomeContentRequest(long userId2) {
        this.userId = userId2;
    }

    public Type successResponseType() {
        return BusinessTravelWelcomeContentResponse.class;
    }

    public String getPath() {
        return BASE_PATH + this.userId;
    }
}
