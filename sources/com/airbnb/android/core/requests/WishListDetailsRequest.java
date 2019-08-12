package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.WishListResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class WishListDetailsRequest extends BaseRequestV2<WishListResponse> {
    private final boolean useMemberFormat;
    private final long wishListId;

    public WishListDetailsRequest(long wishListId2, boolean useMemberFormat2) {
        this.wishListId = wishListId2;
        this.useMemberFormat = useMemberFormat2;
        doubleResponse();
    }

    public String getPath() {
        return "wishlists/" + this.wishListId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.useMemberFormat ? "for_mobile_details" : "for_mobile_public");
    }

    public long getCacheTimeoutMs() {
        return 2419200000L;
    }

    public Type successResponseType() {
        return WishListResponse.class;
    }
}
