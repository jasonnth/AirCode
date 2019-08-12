package com.airbnb.android.itinerary.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.data.models.FreeTimeItem;
import com.airbnb.android.itinerary.responses.SuggestionsResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class SuggestionsRequest extends BaseRequestV2<SuggestionsResponse> {
    private static final String ARG_CONFIRMATION_CODE = "confirmation_code";
    private static final String ARG_ENDS_AT = "ends_at";
    private static final String ARG_STARTS_AT = "starts_at";
    private static final long SOFT_TTL = 300000;
    private static final long TTL = 1800000;
    private final String confirmationCode;
    private final String endsAt;
    private final String startsAt;

    public static SuggestionsRequest newInstance(FreeTimeItem freeTimeItem) {
        return new SuggestionsRequest(freeTimeItem);
    }

    private SuggestionsRequest(FreeTimeItem freeTimeItem) {
        this.startsAt = freeTimeItem.startsAt().getIsoDateStringUTC();
        this.endsAt = freeTimeItem.endsAt().getIsoDateStringUTC();
        this.confirmationCode = freeTimeItem.confirmationCode();
    }

    public Type successResponseType() {
        return SuggestionsResponse.class;
    }

    public String getPath() {
        return "itinerary_recommendation_cards";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11639kv("starts_at", this.startsAt).mo11639kv("ends_at", this.endsAt).mo11639kv("confirmation_code", this.confirmationCode));
    }

    public long getCacheTimeoutMs() {
        return TTL;
    }

    public long getCacheOnlyTimeoutMs() {
        return SOFT_TTL;
    }
}
