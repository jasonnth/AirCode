package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.responses.PayoutInfoTypesResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class PayoutInfoTypesRequest extends BaseRequestV2<PayoutInfoTypesResponse> {
    private final String countryCode;

    public static PayoutInfoTypesRequest forCountry(String countryCode2) {
        return new PayoutInfoTypesRequest(countryCode2);
    }

    private PayoutInfoTypesRequest(String countryCode2) {
        this.countryCode = countryCode2;
    }

    public String getPath() {
        return "payout_info_types";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("country", this.countryCode).mo7894kv("user_id", AirbnbAccountManager.currentUserId());
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public Type successResponseType() {
        return PayoutInfoTypesResponse.class;
    }
}
