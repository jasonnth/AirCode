package com.airbnb.android.wishlists.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import retrofit2.Query;

public class WishlistedListingsRequest extends BaseRequestV2<WishlistedListingsResponse> {
    private final AirDate checkIn;
    private final AirDate checkOut;
    private final GuestDetails guestFilters;
    private final List<Long> listingIds;
    private final boolean publicRequest;
    private final long wishlistId;

    public static WishlistedListingsRequest forPublic(WishList wishList) {
        return new WishlistedListingsRequest(wishList, true);
    }

    public static WishlistedListingsRequest forMembers(WishList wishList) {
        return new WishlistedListingsRequest(wishList, false);
    }

    private WishlistedListingsRequest(WishList wishList, boolean publicRequest2) {
        this.publicRequest = publicRequest2;
        this.wishlistId = wishList.getId();
        this.checkIn = wishList.getCheckin();
        this.checkOut = wishList.getCheckout();
        this.guestFilters = wishList.getGuestDetails();
        this.listingIds = ImmutableList.copyOf((Collection<? extends E>) wishList.getListingIds());
    }

    public String getPath() {
        return "wishlisted_listings";
    }

    public Collection<Query> getQueryParams() {
        String str;
        String str2 = null;
        QueryStrap strap = QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, this.publicRequest ? "default" : "for_mobile_details_v2_collaborator").mo7894kv("wishlist_id", this.wishlistId).mo7895kv("_order", "availability ASC");
        if (!this.publicRequest && this.guestFilters != null) {
            String str3 = "check_in";
            if (this.checkIn != null) {
                str = this.checkIn.getIsoDateString();
            } else {
                str = null;
            }
            QueryStrap kv = strap.mo7895kv(str3, str);
            String str4 = "check_out";
            if (this.checkOut != null) {
                str2 = this.checkOut.getIsoDateString();
            }
            kv.mo7895kv(str4, str2).mo7893kv("number_of_adults", this.guestFilters.adultsCount()).mo7893kv("number_of_children", this.guestFilters.childrenCount()).mo7893kv("number_of_infants", this.guestFilters.infantsCount());
        }
        return strap;
    }

    public long getCacheTimeoutMs() {
        return 1209600000;
    }

    public Type successResponseType() {
        return WishlistedListingsResponse.class;
    }

    public boolean hasSameListings(WishList wishList) {
        return this.listingIds.equals(wishList.getListingIds());
    }
}
