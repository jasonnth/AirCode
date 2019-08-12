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

public class WishListedPlacesRequest extends BaseRequestV2<WishListedPlacesResponse> {
    private static final int NUM_ITEMS_PER_PAGE = 30;
    private final List<Long> placeIds;
    private final boolean publicRequest;
    private final long wishlistId;

    public static WishListedPlacesRequest forPublic(WishList wishList) {
        return new WishListedPlacesRequest(wishList, true);
    }

    public static WishListedPlacesRequest forMembers(WishList wishList) {
        return new WishListedPlacesRequest(wishList, false);
    }

    private WishListedPlacesRequest(WishList wishlist, boolean publicRequest2) {
        this.wishlistId = wishlist.getId();
        this.publicRequest = publicRequest2;
        this.placeIds = ImmutableList.copyOf((Collection<? extends E>) wishlist.getPlaceIds());
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.publicRequest ? "default" : "for_collaborator").mo7894kv("collection_id", this.wishlistId).mo7893kv(TimelineRequest.ARG_LIMIT, 30);
    }

    public Type successResponseType() {
        return WishListedPlacesResponse.class;
    }

    public String getPath() {
        return "collection_places";
    }

    public long getCacheTimeoutMs() {
        return 2419200000L;
    }

    public boolean hasSamePlaces(WishList wishList) {
        return this.placeIds.equals(wishList.getPlaceIds());
    }
}
