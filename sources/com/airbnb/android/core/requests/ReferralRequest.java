package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.GetUserReferralResponse;
import com.airbnb.android.sharing.referral.SharingManager;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ReferralRequest extends BaseRequestV2<GetUserReferralResponse> {
    private final long userId;

    public ReferralRequest(long userId2) {
        this.userId = userId2;
    }

    public String getPath() {
        return SharingManager.REFERRALS;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", this.userId);
    }

    public Type successResponseType() {
        return GetUserReferralResponse.class;
    }
}
