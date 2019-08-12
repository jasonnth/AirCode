package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.ReferralStatus;
import com.airbnb.android.core.responses.ReferralStatusResponse;
import com.airbnb.android.core.utils.DateHelper;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

@Deprecated
public class ReferralStatusRequest extends BaseRequestV2<ReferralStatusResponse> {
    private final long userId;

    public static ReferralStatusRequest newInstance(long userId2) {
        return new ReferralStatusRequest(userId2);
    }

    private ReferralStatusRequest(long userId2) {
        this.userId = userId2;
    }

    public AirResponse<ReferralStatusResponse> transformResponse(AirResponse<ReferralStatusResponse> response) {
        ReferralStatusResponse data = (ReferralStatusResponse) response.body();
        data.offerReferrerCreditGuest = ((ReferralStatus) data.referralStatuses.get(0)).getReferralOffer().getLocalizedCreditGuest();
        data.offerReferrerCreditHost = ((ReferralStatus) data.referralStatuses.get(0)).getReferralOffer().getLocalizedCreditHost();
        return response;
    }

    public String getPath() {
        return "referral_statuses";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", this.userId);
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public Type successResponseType() {
        return ReferralStatusResponse.class;
    }
}
