package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.LocalAttractionsResponse;
import com.airbnb.android.core.utils.DateHelper;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public class LocalAttractionsRequest extends BaseRequestV2<LocalAttractionsResponse> {
    private final long listingId;

    public LocalAttractionsRequest(long listingId2, BaseRequestListener<LocalAttractionsResponse> listener) {
        this(listingId2);
        withListener((Observer) listener);
    }

    public LocalAttractionsRequest(long listingId2) {
        this.listingId = listingId2;
    }

    public String getPath() {
        return "local_attractions";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("listing_id", this.listingId);
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public long getCacheOnlyTimeoutMs() {
        return 3600000;
    }

    public Type successResponseType() {
        return LocalAttractionsResponse.class;
    }
}
