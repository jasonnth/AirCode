package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.ListingPickerInfoResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class ListingPickerInfoRequest extends BaseRequestV2<ListingPickerInfoResponse> {
    public static final int FETCH_SIZE_MANAGE_LISTING_PICKER = 50;
    private final Strap params;

    private ListingPickerInfoRequest(Strap params2) {
        this.params = params2;
    }

    public static ListingPickerInfoRequest forUserId(long userId) {
        return new ListingPickerInfoRequest(Strap.make().mo11638kv("user_id", userId).mo11637kv(TimelineRequest.ARG_OFFSET, 0).mo11639kv(TimelineRequest.ARG_FORMAT, "for_manage_listing_app").mo11637kv(TimelineRequest.ARG_LIMIT, 50).mo11640kv(ListingRequestConstants.JSON_HAS_AVAILABILITY, false));
    }

    public Type successResponseType() {
        return ListingPickerInfoResponse.class;
    }

    public String getPath() {
        return "listings";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }
}
