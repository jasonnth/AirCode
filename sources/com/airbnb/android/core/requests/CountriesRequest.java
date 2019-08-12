package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.CountriesResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Map;

public class CountriesRequest extends BaseRequestV2<CountriesResponse> {
    private final Strap queryParams;

    public static CountriesRequest forCountryOfIP() {
        return new CountriesRequest(Strap.make().mo11639kv("ip", "me"));
    }

    public static CountriesRequest forAllCountries() {
        return new CountriesRequest(Strap.make());
    }

    public static CountriesRequest forNewPayoutSupportingCountries() {
        return new CountriesRequest(Strap.make().mo11640kv("supported_new_payout_forms", true));
    }

    private CountriesRequest(Strap queryParams2) {
        this.queryParams = queryParams2;
    }

    public String getPath() {
        return "countries";
    }

    public QueryStrap getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.queryParams);
    }

    public Type successResponseType() {
        return CountriesResponse.class;
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public long getCacheOnlyTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }
}
