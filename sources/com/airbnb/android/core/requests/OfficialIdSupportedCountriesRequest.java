package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.android.core.responses.OfficialIdSupportedCountriesResponse;
import com.airbnb.android.core.utils.DateHelper;
import java.lang.reflect.Type;

public class OfficialIdSupportedCountriesRequest extends BaseRequest<OfficialIdSupportedCountriesResponse> {
    public OfficialIdSupportedCountriesRequest(BaseRequestListener<OfficialIdSupportedCountriesResponse> listener) {
        withListener(listener);
    }

    public String getPath() {
        return "official_id/supported";
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public Type successResponseType() {
        return OfficialIdSupportedCountriesResponse.class;
    }
}
