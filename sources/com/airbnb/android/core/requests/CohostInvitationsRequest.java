package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.CohostInvitationsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class CohostInvitationsRequest extends BaseRequestV2<CohostInvitationsResponse> {
    private final Strap queryParams;

    private CohostInvitationsRequest(Strap params) {
        this.queryParams = params;
    }

    public static CohostInvitationsRequest forListing(long listingId, long inviterId) {
        return new CohostInvitationsRequest(Strap.make().mo11638kv("listing_id", listingId).mo11638kv("inviter_id", inviterId).mo11639kv(TimelineRequest.ARG_FORMAT, "for_manage_listing"));
    }

    public String getPath() {
        return "cohost_invitations";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.queryParams);
    }

    public Type successResponseType() {
        return CohostInvitationsResponse.class;
    }
}
