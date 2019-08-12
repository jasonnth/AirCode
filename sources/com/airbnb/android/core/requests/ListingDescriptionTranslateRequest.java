package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.ListingDescriptionTranslateResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.utils.LanguageUtils;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ListingDescriptionTranslateRequest extends BaseRequestV2<ListingDescriptionTranslateResponse> {
    private final long listingId;

    public ListingDescriptionTranslateRequest(long listingId2) {
        this.listingId = listingId2;
    }

    public String getPath() {
        return "listing_descriptions/" + this.listingId + "/" + LanguageUtils.getLanguage();
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public long getCacheOnlyTimeoutMs() {
        return 86400000;
    }

    public Type successResponseType() {
        return ListingDescriptionTranslateResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7893kv("_remove_legacy_translation", 1);
    }
}
