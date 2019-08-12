package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.DomainsResponse;
import com.airbnb.android.core.utils.DateHelper;
import java.lang.reflect.Type;

public class DomainsRequest extends BaseRequestV2<DomainsResponse> {
    public String getPath() {
        return "domains";
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public long getCacheOnlyTimeoutMs() {
        return 86400000;
    }

    public Type successResponseType() {
        return DomainsResponse.class;
    }
}
