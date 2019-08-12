package com.airbnb.android.payout.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class PayoutInfoFormRequest extends BaseRequestV2<PayoutInfoFormResponse> {
    private static final String PATH = "payout_info_forms";
    private final String countryCode;
    private final long userId;

    public static PayoutInfoFormRequest withCountryCode(String countryCode2, long userId2) {
        return new PayoutInfoFormRequest(countryCode2, userId2);
    }

    private PayoutInfoFormRequest(String countryCode2, long userId2) {
        this.countryCode = countryCode2;
        this.userId = userId2;
    }

    public Type successResponseType() {
        return PayoutInfoFormResponse.class;
    }

    public String getPath() {
        return PATH;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix(super.getQueryParams()).mo7895kv("country", this.countryCode).mo7894kv("user_id", this.userId);
    }
}
