package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.responses.WishListResponse;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.core.wishlists.WishListableType;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.erf.Experiments;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import retrofit2.Query;

public class CreateWishListRequest extends BaseRequestV2<WishListResponse> {
    private final Body body;

    private static class Body {
        @JsonProperty
        List<Long> articleIds;
        @JsonProperty
        AirDate checkin;
        @JsonProperty
        AirDate checkout;
        @JsonProperty("private")
        boolean isPrivate;
        @JsonProperty
        List<Long> listingIds;
        @JsonProperty
        List<Long> mtTemplateIds;
        @JsonProperty
        String name;
        @JsonProperty
        Integer numberOfAdults;
        @JsonProperty
        Integer numberOfChildren;
        @JsonProperty
        Integer numberOfInfants;
        @JsonProperty
        Boolean pets;
        @JsonProperty
        List<Long> placeActivityIds;
        @JsonProperty
        List<Long> placeIds;

        public Body(String name2, boolean isPrivate2, WishListableData data) {
            this.name = name2;
            this.isPrivate = isPrivate2;
            switch (C62461.$SwitchMap$com$airbnb$android$core$wishlists$WishListableType[data.type().ordinal()]) {
                case 1:
                    this.listingIds = Collections.singletonList(Long.valueOf(data.itemId()));
                    break;
                case 2:
                    this.placeIds = Collections.singletonList(Long.valueOf(data.itemId()));
                    break;
                case 3:
                    this.placeActivityIds = Collections.singletonList(Long.valueOf(data.itemId()));
                    break;
                case 4:
                    this.mtTemplateIds = Collections.singletonList(Long.valueOf(data.itemId()));
                    break;
                case 5:
                    this.articleIds = Collections.singletonList(Long.valueOf(data.itemId()));
                    break;
                default:
                    throw new IllegalStateException("unknown type: " + data.type());
            }
            GuestDetails guestDetails = data.guestDetails();
            if (guestDetails != null && Experiments.showImprovedWishListFiltersUx()) {
                this.pets = Boolean.valueOf(guestDetails.isBringingPets());
                this.numberOfAdults = Integer.valueOf(guestDetails.getNumberOfAdults());
                this.numberOfChildren = Integer.valueOf(guestDetails.getNumberOfChildren());
                this.numberOfInfants = Integer.valueOf(guestDetails.getNumberOfInfants());
            }
            if (data.hasDates() && Experiments.showImprovedWishListFiltersUx()) {
                this.checkin = data.checkIn();
                this.checkout = data.checkOut();
            }
        }
    }

    /* renamed from: com.airbnb.android.core.requests.CreateWishListRequest$1 */
    static /* synthetic */ class C62461 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$android$core$wishlists$WishListableType = new int[WishListableType.values().length];

        static {
            try {
                $SwitchMap$com$airbnb$android$core$wishlists$WishListableType[WishListableType.Home.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$airbnb$android$core$wishlists$WishListableType[WishListableType.Place.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$airbnb$android$core$wishlists$WishListableType[WishListableType.PlaceActivity.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$airbnb$android$core$wishlists$WishListableType[WishListableType.Trip.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$airbnb$android$core$wishlists$WishListableType[WishListableType.StoryArticle.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public CreateWishListRequest(String name, WishListableData data, boolean isPrivate) {
        this.body = new Body(name, isPrivate, data);
    }

    public Body getBody() {
        return this.body;
    }

    public String getPath() {
        return "wishlists";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_details");
    }

    public Type successResponseType() {
        return WishListResponse.class;
    }
}
