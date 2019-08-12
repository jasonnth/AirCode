package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.WishListMembershipsResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class WishListMembershipsRequest extends BaseRequestV2<WishListMembershipsResponse> {
    private final long wishListId;

    public WishListMembershipsRequest(long wishListId2) {
        this.wishListId = wishListId2;
    }

    public String getPath() {
        return "wishlist_memberships";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("wishlist_id", this.wishListId);
    }

    public long getCacheTimeoutMs() {
        return 2419200000L;
    }

    public Type successResponseType() {
        return WishListMembershipsResponse.class;
    }
}
