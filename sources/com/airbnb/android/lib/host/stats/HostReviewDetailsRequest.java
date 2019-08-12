package com.airbnb.android.lib.host.stats;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.google.common.collect.FluentIterable;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import retrofit2.Query;

public class HostReviewDetailsRequest extends BaseRequestV2<HostReviewDetailsResponse> {
    private final QueryStrap strap;

    public static HostReviewDetailsRequest forListing(Listing listing) {
        return new HostReviewDetailsRequest(listing);
    }

    public static HostReviewDetailsRequest forListings(List<Listing> listings) {
        return new HostReviewDetailsRequest(listings);
    }

    public HostReviewDetailsRequest(Listing listing) {
        this.strap = QueryStrap.make().mo7894kv("listing_ids", listing.getId()).mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_stats_reviews");
    }

    private HostReviewDetailsRequest(List<Listing> listings) {
        this.strap = QueryStrap.make().mo7895kv("listing_ids", TextUtils.join(",", FluentIterable.from((Iterable<E>) listings).transform(HostReviewDetailsRequest$$Lambda$1.lambdaFactory$()).filter(HostReviewDetailsRequest$$Lambda$2.lambdaFactory$()).toList())).mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_stats_reviews");
    }

    static /* synthetic */ Long lambda$new$0(Listing input) {
        if (input == null) {
            return null;
        }
        return Long.valueOf(input.getId());
    }

    static /* synthetic */ boolean lambda$new$1(Long input) {
        return input != null;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Collection<Query>) this.strap);
    }

    public Type successResponseType() {
        return HostReviewDetailsResponse.class;
    }

    public String getPath() {
        return "review_rating_aggregations";
    }
}
