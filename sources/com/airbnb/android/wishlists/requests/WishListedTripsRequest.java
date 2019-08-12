package com.airbnb.android.wishlists.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import retrofit2.Query;

public class WishListedTripsRequest extends BaseRequestV2<WishListedTripsResponse> {
    private static final int NUM_ITEMS_PER_PAGE = 30;
    private final boolean publicRequest;
    private final List<Long> tripIds;
    private final long wishlistId;

    public static WishListedTripsRequest forPublic(WishList wishList) {
        return new WishListedTripsRequest(wishList, true);
    }

    public static WishListedTripsRequest forMembers(WishList wishList) {
        return new WishListedTripsRequest(wishList, false);
    }

    private WishListedTripsRequest(WishList wishlist, boolean publicRequest2) {
        this.wishlistId = wishlist.getId();
        this.publicRequest = publicRequest2;
        this.tripIds = ImmutableList.copyOf((Collection<? extends E>) wishlist.getTripIds());
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.publicRequest ? "default" : "for_collaborator").mo7894kv("collection_id", this.wishlistId).mo7893kv(TimelineRequest.ARG_LIMIT, 30);
    }

    public Type successResponseType() {
        return WishListedTripsResponse.class;
    }

    public String getPath() {
        return "collection_mt_templates";
    }

    public long getCacheTimeoutMs() {
        return 2419200000L;
    }

    public boolean hasSameTrips(WishList wishList) {
        return this.tripIds.equals(wishList.getTripIds());
    }
}
