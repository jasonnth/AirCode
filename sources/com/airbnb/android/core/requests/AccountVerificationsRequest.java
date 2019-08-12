package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.intents.VerifiedIdActivityIntents;
import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class AccountVerificationsRequest extends BaseRequestV2<AccountVerificationsResponse> {
    private final Strap params;

    private AccountVerificationsRequest(Strap params2) {
        this.params = params2;
    }

    public String getPath() {
        return VerifiedIdActivityIntents.VERIFICATIONS_EXTRA;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public static AccountVerificationsRequest forFlow(VerificationFlow verificationFlow) {
        return new AccountVerificationsRequest(Strap.make().mo11639kv(ManageListingAnalytics.FLOW, verificationFlow.getRequestFilter().getParamName()));
    }

    public Type successResponseType() {
        return AccountVerificationsResponse.class;
    }
}
