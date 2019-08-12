package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.HostReferralReferrerInfo;
import com.airbnb.android.core.responses.GetHostReferralInfoResponse;
import com.airbnb.android.core.utils.DateHelper;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetHostReferralInfoRequest extends BaseRequestV2<GetHostReferralInfoResponse> {
    private final long userId;

    public GetHostReferralInfoRequest(long userId2) {
        this.userId = userId2;
    }

    public AirResponse<GetHostReferralInfoResponse> transformResponse(AirResponse<GetHostReferralInfoResponse> response) {
        GetHostReferralInfoResponse data = (GetHostReferralInfoResponse) response.body();
        if (!data.hostReferralInfos.isEmpty()) {
            data.info = (HostReferralReferrerInfo) data.hostReferralInfos.get(0);
        }
        return response;
    }

    public String getPath() {
        return "host_referral_referrer_infos";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", this.userId);
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public Type successResponseType() {
        return GetHostReferralInfoResponse.class;
    }
}
