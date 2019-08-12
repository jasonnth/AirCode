package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.p009p3.P3State;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class P3ListingRequest extends BaseRequestV2<ListingResponse> {
    private static final String FORMAT = "_format";
    private static final String INTENT = "_intent";
    private final long listingId;
    private final Strap params;

    private P3ListingRequest(long listingId2, Strap strap) {
        this.listingId = listingId2;
        this.params = strap;
        this.params.mo11639kv("_source", "mobile_p3");
    }

    public static P3ListingRequest forListingId(long listingId2) {
        return forP3(P3State.builder().listingId(listingId2).build());
    }

    public static P3ListingRequest forP3(P3State state) {
        return new P3ListingRequest(state.listingId(), getQueryParams(state).mo11639kv("_format", "for_guidebooks").mo11639kv(INTENT, "vendor_translation_exp"));
    }

    public static P3ListingRequest forTranslatedHouseRules(long listingId2) {
        return new P3ListingRequest(listingId2, Strap.make().mo11639kv("_format", "for_localized_house_rules"));
    }

    public String getPath() {
        return "listings/" + this.listingId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }

    public long getCacheOnlyTimeoutMs() {
        return 1200000;
    }

    public long getCacheTimeoutMs() {
        return 1209600000;
    }

    private static Strap getQueryParams(P3State state) {
        Strap strap = Strap.make();
        AirDate checkinDate = state.checkInDate();
        if ((checkinDate != null && checkinDate.isSameDayOrAfter(AirDate.yesterday())) && state.checkOutDate() != null) {
            strap.mo11639kv(UpdateReviewRequest.KEY_CHECKIN, checkinDate.getIsoDateString());
            strap.mo11637kv("nights", checkinDate.getDaysUntil(state.checkOutDate()));
        }
        strap.mo11637kv("number_of_guests", state.guestDetails() != null ? state.guestDetails().adultsCount() : GuestDetails.defaultNumAdults());
        return strap;
    }

    public Type successResponseType() {
        return ListingResponse.class;
    }
}
