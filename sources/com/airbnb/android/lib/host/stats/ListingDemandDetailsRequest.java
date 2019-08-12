package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.User;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ListingDemandDetailsRequest extends BaseRequestV2<ListingDemandDetailsResponse> {
    private final QueryStrap strap;

    private ListingDemandDetailsRequest(QueryStrap strap2) {
        this.strap = strap2;
    }

    public static ListingDemandDetailsRequest forUser(User user) {
        return new ListingDemandDetailsRequest(QueryStrap.make().mo7894kv("user_id", user.getId()));
    }

    public Type successResponseType() {
        return ListingDemandDetailsResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return this.strap.mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_view_details");
    }

    public String getPath() {
        return "listings";
    }
}
