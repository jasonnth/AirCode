package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.SupportPhoneNumbersResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.lib.analytics.SupportPhoneNumberAnalytics;
import java.lang.reflect.Type;

public class SupportPhoneNumbersRequest extends BaseRequestV2<SupportPhoneNumbersResponse> {
    public SupportPhoneNumbersRequest() {
        doubleResponse();
    }

    public String getPath() {
        return SupportPhoneNumberAnalytics.EVENT_SUPPORT_PHONE_NUMBER;
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_YEAR_MILLIS;
    }

    public long getCacheOnlyTimeoutMs() {
        return 86400000;
    }

    public Type successResponseType() {
        return SupportPhoneNumbersResponse.class;
    }
}
