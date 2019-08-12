package com.airbnb.android.core.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.FetchPricingInteractionType;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.responses.PricingQuoteResponse;
import com.airbnb.android.core.utils.SearchUtil;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class PricingQuoteRequest extends BaseRequestV2<PricingQuoteResponse> {
    private static final int MIN_ADULTS = 1;
    private final AirDate checkinDate;
    private final AirDate checkoutDate;
    private final GuestDetails guestDetails;
    private final FetchPricingInteractionType interactionType;
    private final long listingId;
    private final String p3ImpressionId;
    private final String requestIntent;

    public PricingQuoteRequest(long listingId2, AirDate checkinDate2, AirDate checkoutDate2, GuestDetails data, FetchPricingInteractionType interactionType2, String p3ImpressionId2, String requestIntent2) {
        this.listingId = listingId2;
        this.checkinDate = checkinDate2;
        this.checkoutDate = checkoutDate2;
        this.interactionType = interactionType2;
        this.p3ImpressionId = p3ImpressionId2;
        this.requestIntent = requestIntent2;
        this.guestDetails = data;
    }

    public String getPath() {
        return "pricing_quotes";
    }

    public Collection<Query> getQueryParams() {
        QueryStrap strap = QueryStrap.make().mo7894kv("listing_id", this.listingId).mo7895kv(TimelineRequest.ARG_FORMAT, "for_detailed_booking_info_with_message_data");
        if (this.guestDetails != null) {
            strap.mo7893kv("number_of_adults", Math.max(1, this.guestDetails.adultsCount())).mo7893kv("number_of_children", this.guestDetails.childrenCount()).mo7893kv("number_of_infants", this.guestDetails.infantsCount());
        }
        if (!(this.checkinDate == null || this.checkoutDate == null)) {
            strap.mo7895kv("check_in", this.checkinDate.getIsoDateString());
            strap.mo7895kv("check_out", this.checkoutDate.getIsoDateString());
        }
        SearchUtil.addSupportedUrgencyTypesToRequestStrap(strap);
        if (!TextUtils.isEmpty(this.requestIntent)) {
            strap.mo7895kv("_intents", this.requestIntent);
        }
        return strap;
    }

    public Strap getHeaders() {
        return Strap.make().mo11639kv("X-Airbnb-Meta-p3-impression-id", this.p3ImpressionId).mo11639kv("X-Airbnb-Meta-interaction-type", this.interactionType.getServerKey());
    }

    public AirResponse<PricingQuoteResponse> transformResponse(AirResponse<PricingQuoteResponse> response) {
        PricingQuoteResponse data = (PricingQuoteResponse) response.body();
        if (!ListUtils.isEmpty((Collection<?>) data.pricingQuotes)) {
            data.pricingQuote = (PricingQuote) data.pricingQuotes.get(0);
        }
        return response;
    }

    public long getCacheTimeoutMs() {
        return 3600000;
    }

    public Type successResponseType() {
        return PricingQuoteResponse.class;
    }
}
