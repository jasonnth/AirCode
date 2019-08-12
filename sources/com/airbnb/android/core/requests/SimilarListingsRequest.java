package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.responses.SimilarListingsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class SimilarListingsRequest extends BaseRequestV2<SimilarListingsResponse> {
    private final Strap queryParams;

    private SimilarListingsRequest(Strap params) {
        this.queryParams = params;
    }

    public static SimilarListingsRequest forUser(User user) {
        return new SimilarListingsRequest(Strap.make().mo11638kv("user_id", user.getId()).mo11639kv(TimelineRequest.ARG_FORMAT, "for_listing_card"));
    }

    public static SimilarListingsRequest forP3(AirDate checkIn, AirDate checkOut, GuestDetails filterData, long listingId) {
        Strap strap = Strap.make().mo11638kv("listing_id", listingId).mo11639kv(TimelineRequest.ARG_FORMAT, "for_listing_card");
        if (filterData != null && filterData.totalGuestCount() > 0) {
            strap.mo11637kv(FindTweenAnalytics.GUESTS, filterData.totalGuestCount());
        }
        if (!(checkIn == null || checkOut == null)) {
            strap.mo11639kv("check_in", checkIn.getIsoDateString());
            strap.mo11639kv("check_out", checkOut.getIsoDateString());
        }
        strap.mo11640kv("filter_instant_book", true);
        return new SimilarListingsRequest(strap);
    }

    public static SimilarListingsRequest forArticle(long articleId) {
        return new SimilarListingsRequest(Strap.make().mo11638kv("article_id", articleId).mo11639kv(TimelineRequest.ARG_FORMAT, "for_similar_listings_with_prices"));
    }

    public static SimilarListingsRequest forListing(long listingId) {
        return new SimilarListingsRequest(Strap.make().mo11638kv("listing_id", listingId).mo11639kv(TimelineRequest.ARG_FORMAT, "for_listing_card"));
    }

    public String getPath() {
        return P3Arguments.FROM_SIMILAR_LISTINGS;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.queryParams);
    }

    public Type successResponseType() {
        return SimilarListingsResponse.class;
    }

    public long getCacheTimeoutMs() {
        return 1209600000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 600000;
    }
}
