package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.GetSavedChinaIdentitesResponse;
import java.lang.reflect.Type;

public class GetSavedChinaIdentitesRequest extends BaseRequestV2<GetSavedChinaIdentitesResponse> {
    public String getPath() {
        return "china_resident_identity_cards";
    }

    public Type successResponseType() {
        return GetSavedChinaIdentitesResponse.class;
    }
}
