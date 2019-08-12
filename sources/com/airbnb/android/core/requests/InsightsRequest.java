package com.airbnb.android.core.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.responses.InsightsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.google.common.collect.FluentIterable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import retrofit2.Query;

public class InsightsRequest extends BaseRequestV2<InsightsResponse> {
    public static final int MOBILE_PLACEMENT_NEW = 6;
    private String format;
    private Listing listing;

    public static InsightsRequest forListing(Listing listing2) {
        return new InsightsRequest(listing2, false);
    }

    public static InsightsRequest forMetaData() {
        return new InsightsRequest(null, true);
    }

    private InsightsRequest() {
    }

    private InsightsRequest(Listing listing2, boolean forMetaData) {
        this.listing = listing2;
        this.format = forMetaData ? "for_metadata" : "for_client";
    }

    private InsightsRequest(boolean forMetaData) {
        this(null, forMetaData);
    }

    public Type successResponseType() {
        return InsightsResponse.class;
    }

    public String getPath() {
        return "stories";
    }

    public Collection<Query> getQueryParams() {
        QueryStrap strap = QueryStrap.make().mo7893kv("placement", 6);
        if (!TextUtils.isEmpty(this.format)) {
            strap.mo7895kv(TimelineRequest.ARG_FORMAT, this.format);
        }
        if (this.listing != null) {
            strap.mo7894kv("listing_id", this.listing.getId());
        }
        return strap;
    }

    public AirResponse<InsightsResponse> transformResponse(AirResponse<InsightsResponse> response) {
        InsightsResponse insightsResponse = (InsightsResponse) response.body();
        insightsResponse.stories = FluentIterable.from((Iterable<E>) ((InsightsResponse) response.body()).stories).filter(InsightsRequest$$Lambda$1.lambdaFactory$()).toList();
        for (Insight story : ((InsightsResponse) response.body()).stories) {
            story.setListing(this.listing);
        }
        ((InsightsResponse) response.body()).stories = new ArrayList(((InsightsResponse) response.body()).stories);
        return response;
    }
}
