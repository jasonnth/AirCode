package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.core.responses.ReferralStatusForMobileResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ReferralStatusForMobileRequest extends BaseRequestV2<ReferralStatusForMobileResponse> {
    private final long userId;

    public static ReferralStatusForMobileRequest newInstance(long userId2) {
        return new ReferralStatusForMobileRequest(userId2);
    }

    private ReferralStatusForMobileRequest(long userId2) {
        this.userId = userId2;
    }

    public String getPath() {
        return "referral_statuses";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", this.userId).mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile");
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public AirResponse<ReferralStatusForMobileResponse> transformResponse(AirResponse<ReferralStatusForMobileResponse> response) {
        ((ReferralStatusForMobileResponse) response.body()).referralStatus = (ReferralStatusForMobile) ((ReferralStatusForMobileResponse) response.body()).referralStatuses.get(0);
        return super.transformResponse(response);
    }

    public Type successResponseType() {
        return ReferralStatusForMobileResponse.class;
    }
}
