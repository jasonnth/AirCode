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

public class WishListedPlaceActivitiesRequest extends BaseRequestV2<WishListedPlaceActivitiesResponse> {
    private static final int NUM_ITEMS_PER_PAGE = 30;
    private final List<Long> placeActivityIds;
    private final boolean publicRequest;
    private final long wishlistId;

    public static WishListedPlaceActivitiesRequest forPublic(WishList wishList) {
        return new WishListedPlaceActivitiesRequest(wishList, true);
    }

    public static WishListedPlaceActivitiesRequest forMembers(WishList wishList) {
        return new WishListedPlaceActivitiesRequest(wishList, false);
    }

    private WishListedPlaceActivitiesRequest(WishList wishlist, boolean publicRequest2) {
        this.wishlistId = wishlist.getId();
        this.publicRequest = publicRequest2;
        this.placeActivityIds = ImmutableList.copyOf((Collection<? extends E>) wishlist.getPlaceActivityIds());
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.publicRequest ? "default" : "for_collaborator").mo7894kv("collection_id", this.wishlistId).mo7893kv(TimelineRequest.ARG_LIMIT, 30);
    }

    public Type successResponseType() {
        return WishListedPlaceActivitiesResponse.class;
    }

    public String getPath() {
        return "collection_activities";
    }

    public long getCacheTimeoutMs() {
        return 2419200000L;
    }

    public boolean hasSamePlaceActivities(WishList wishList) {
        return this.placeActivityIds.equals(wishList.getPlaceActivityIds());
    }
}
