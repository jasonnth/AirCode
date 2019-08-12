package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.responses.GetExistingPayoutMethodResponse;
import java.lang.reflect.Type;

public class GetExistingPayoutMethodRequest extends BaseRequestV2<GetExistingPayoutMethodResponse> {
    public static GetExistingPayoutMethodRequest forHostPayouts() {
        return new GetExistingPayoutMethodRequest();
    }

    public String getPath() {
        return "payment_instruments";
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public QueryStrap getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", AirbnbAccountManager.currentUserId()).mo7897kv("payout_enabled", true);
    }

    public Type successResponseType() {
        return GetExistingPayoutMethodResponse.class;
    }
}
