package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.GetSavedPassportsResponse;
import java.lang.reflect.Type;

public class GetSavedPassportsRequest extends BaseRequestV2<GetSavedPassportsResponse> {
    public String getPath() {
        return "passports";
    }

    public Type successResponseType() {
        return GetSavedPassportsResponse.class;
    }
}
