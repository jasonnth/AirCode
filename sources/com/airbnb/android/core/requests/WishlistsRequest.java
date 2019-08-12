package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.WishlistsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public class WishlistsRequest extends BaseRequestV2<WishlistsResponse> {
    public static final int NUM_ITEMS_PER_PAGE = 20;
    private final int offset;

    public WishlistsRequest(int offset2, BaseRequestListener<WishlistsResponse> listener) {
        withListener((Observer) listener);
        this.offset = offset2;
    }

    public String getPath() {
        return "wishlists";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_private_index").mo7893kv(TimelineRequest.ARG_LIMIT, 20).mo7897kv("include_shared_wishlists", true).mo7893kv(TimelineRequest.ARG_OFFSET, this.offset);
    }

    public long getCacheTimeoutMs() {
        return this.offset == 0 ? 604800000 : 0;
    }

    public Type successResponseType() {
        return WishlistsResponse.class;
    }
}
