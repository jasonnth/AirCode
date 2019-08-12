package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.responses.WishListResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import retrofit2.Query;

public class UpdateWishListRequest extends BaseRequestV2<WishListResponse> {
    private final Object requestBody;
    private final long wishListId;

    private static class FiltersRequestBody {
        @JsonProperty("number_of_adults")
        final Integer adults;
        @JsonInclude(Include.ALWAYS)
        @JsonProperty("checkin")
        final AirDate checkIn;
        @JsonInclude(Include.ALWAYS)
        @JsonProperty("checkout")
        final AirDate checkOut;
        @JsonProperty("number_of_children")
        final Integer children;
        @JsonProperty("number_of_infants")
        final Integer infants;
        @JsonProperty("pets")
        final Boolean pets;

        public FiltersRequestBody(AirDate checkIn2, AirDate checkOut2, GuestDetails guestFilters) {
            this.checkIn = checkIn2;
            this.checkOut = checkOut2;
            if (guestFilters == null) {
                this.adults = null;
                this.children = null;
                this.infants = null;
                this.pets = null;
                return;
            }
            this.pets = Boolean.valueOf(guestFilters.isBringingPets());
            this.adults = Integer.valueOf(guestFilters.adultsCount());
            this.children = Integer.valueOf(guestFilters.childrenCount());
            this.infants = Integer.valueOf(guestFilters.infantsCount());
        }
    }

    private static class ListingsRequestBody {
        @JsonProperty("listing_ids")
        protected final List<Long> listingIds;

        ListingsRequestBody(List<Long> listingIds2) {
            this.listingIds = ImmutableList.copyOf((Collection<? extends E>) listingIds2);
        }
    }

    public static UpdateWishListRequest forDateAndFilters(long wishlistId, AirDate checkIn, AirDate checkOut, GuestDetails guestFilters) {
        return new UpdateWishListRequest(wishlistId, new FiltersRequestBody(checkIn, checkOut, guestFilters));
    }

    public static UpdateWishListRequest setPrivate(long wishlistId, boolean isPrivate) {
        return new UpdateWishListRequest(wishlistId, Strap.make().mo11640kv("private", isPrivate));
    }

    public static UpdateWishListRequest forListings(WishList wishlist) {
        return new UpdateWishListRequest(wishlist.getId(), new ListingsRequestBody(wishlist.getListingIds()));
    }

    private UpdateWishListRequest(long wishListId2, Object requestBody2) {
        this.wishListId = wishListId2;
        this.requestBody = requestBody2;
    }

    public String getPath() {
        return "wishlists/" + this.wishListId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_details");
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Type successResponseType() {
        return WishListResponse.class;
    }
}
