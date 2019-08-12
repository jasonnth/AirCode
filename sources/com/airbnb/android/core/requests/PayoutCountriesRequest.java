package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.android.core.responses.PayoutCountriesResponse;
import com.airbnb.android.core.responses.PayoutCountriesResponse.Country;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Deprecated
public class PayoutCountriesRequest extends BaseRequest<PayoutCountriesResponse> {
    public PayoutCountriesRequest(BaseRequestListener<PayoutCountriesResponse> listener) {
        withListener(listener);
    }

    public String getPath() {
        return "payout_infos/countries";
    }

    public AirResponse<PayoutCountriesResponse> transformResponse(AirResponse<PayoutCountriesResponse> response) {
        PayoutCountriesResponse data = (PayoutCountriesResponse) response.body();
        data.countryCodes = new ArrayList<>();
        data.countryNames = new ArrayList<>();
        for (Country country : data.rawCountries) {
            data.countryCodes.add(country.country_code);
            data.countryNames.add(country.country_name);
        }
        return response;
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 86400000;
    }

    public Type successResponseType() {
        return PayoutCountriesResponse.class;
    }
}
