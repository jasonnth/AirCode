package com.airbnb.android.payout.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class UserAddressRequest extends BaseRequestV2<UserAddressResponse> {
    private static final String PATH = "users/";
    private final String payoutCountry;
    private final long userId;

    public static UserAddressRequest forPayoutCountry(long userId2, String payoutCountry2) {
        return new UserAddressRequest(userId2, payoutCountry2);
    }

    private UserAddressRequest(long userId2, String payoutCountry2) {
        this.userId = userId2;
        this.payoutCountry = payoutCountry2;
    }

    public Type successResponseType() {
        return UserAddressResponse.class;
    }

    public String getPath() {
        return PATH + this.userId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_addresses").mo7895kv("_payout_country", this.payoutCountry);
    }
}
