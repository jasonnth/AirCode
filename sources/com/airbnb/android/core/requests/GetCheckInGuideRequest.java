package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.CheckInGuideResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetCheckInGuideRequest extends BaseRequestV2<CheckInGuideResponse> {
    private static final String PARAM_TARGET_LANGUAGE = "localized_language";
    private final long listingId;
    private final String targetLanguage;

    private GetCheckInGuideRequest(long listingId2, String targetLanguage2) {
        this.listingId = listingId2;
        this.targetLanguage = targetLanguage2;
    }

    public static GetCheckInGuideRequest forListingId(long listingId2, String targetLanguage2) {
        return new GetCheckInGuideRequest(listingId2, targetLanguage2);
    }

    public String getPath() {
        return "check_in_guides/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Type successResponseType() {
        return CheckInGuideResponse.class;
    }

    public Collection<Query> getQueryParams() {
        QueryStrap params = QueryStrap.make();
        if (this.targetLanguage != null) {
            params.mo7895kv(PARAM_TARGET_LANGUAGE, this.targetLanguage);
        }
        return params;
    }
}
