package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.LongTermPricingExampleResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class LongTermPricingExampleRequest extends BaseRequestV2<LongTermPricingExampleResponse> {
    private final long listingId;

    public LongTermPricingExampleRequest(long listingId2) {
        this.listingId = listingId2;
    }

    public String getPath() {
        return "long_term_pricing_examples";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("listing_id", this.listingId);
    }

    public Type successResponseType() {
        return LongTermPricingExampleResponse.class;
    }
}
