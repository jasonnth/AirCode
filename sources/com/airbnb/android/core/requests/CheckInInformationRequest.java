package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.ListingCheckInInformationResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class CheckInInformationRequest extends BaseRequestV2<ListingCheckInInformationResponse> {
    private final long groupId;
    private final long listingId;

    public static CheckInInformationRequest forSelfCheckIn(long listingId2) {
        return new CheckInInformationRequest(listingId2, 1);
    }

    public static CheckInInformationRequest forAllMethods(long listingId2) {
        return new CheckInInformationRequest(listingId2, 0);
    }

    private CheckInInformationRequest(long listingId2, int groupId2) {
        this.listingId = listingId2;
        this.groupId = (long) groupId2;
    }

    public String getPath() {
        return "listing_checkin_informations";
    }

    public Type successResponseType() {
        return ListingCheckInInformationResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("listing_id", this.listingId).mo7894kv("group_id", this.groupId);
    }
}
