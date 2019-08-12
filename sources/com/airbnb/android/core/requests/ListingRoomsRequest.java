package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.ListingRoomsResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class ListingRoomsRequest extends BaseRequestV2<ListingRoomsResponse> {
    private final long listingId;

    public ListingRoomsRequest(long listingId2) {
        this.listingId = listingId2;
    }

    public String getPath() {
        return "listing_rooms/";
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Type successResponseType() {
        return ListingRoomsResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11638kv("listing_id", this.listingId));
    }
}
