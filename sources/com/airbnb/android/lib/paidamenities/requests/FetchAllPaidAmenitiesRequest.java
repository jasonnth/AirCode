package com.airbnb.android.lib.paidamenities.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.lib.paidamenities.fragments.purchase.RequestAmenityFragment;
import com.airbnb.android.lib.paidamenities.responses.FetchAllPaidAmenitiesResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class FetchAllPaidAmenitiesRequest extends BaseRequestV2<FetchAllPaidAmenitiesResponse> {
    private final Format format;
    private final long listingId;

    public enum Format {
        DetailView("for_mobile_detail_view"),
        ListView("for_mobile_list_view");
        
        public final String serverKey;

        private Format(String serverKey2) {
            this.serverKey = serverKey2;
        }
    }

    public static FetchAllPaidAmenitiesRequest forListingId(long listingId2, Format format2) {
        return new FetchAllPaidAmenitiesRequest(listingId2, format2);
    }

    private FetchAllPaidAmenitiesRequest(long listingId2, Format format2) {
        this.listingId = listingId2;
        this.format = format2;
    }

    public Type successResponseType() {
        return FetchAllPaidAmenitiesResponse.class;
    }

    public String getPath() {
        return RequestAmenityFragment.ARG_PAID_AMENITIES;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("listing_id", this.listingId).mo7895kv(TimelineRequest.ARG_FORMAT, this.format.serverKey);
    }
}
