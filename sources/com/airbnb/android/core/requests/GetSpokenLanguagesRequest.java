package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.android.core.responses.GetSpokenLanguagesResponse;
import com.airbnb.android.core.utils.DateHelper;
import java.lang.reflect.Type;

public class GetSpokenLanguagesRequest extends BaseRequest<GetSpokenLanguagesResponse> {
    public GetSpokenLanguagesRequest(BaseRequestListener<GetSpokenLanguagesResponse> listener) {
        withListener(listener);
    }

    public String getPath() {
        return "users/spoken_languages";
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public Type successResponseType() {
        return GetSpokenLanguagesResponse.class;
    }
}
