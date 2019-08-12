package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.TranslateReviewsResponse;
import com.airbnb.android.core.utils.DateHelper;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import retrofit2.Query;

public class TranslateReviewsRequest extends BaseRequestV2<TranslateReviewsResponse> {
    private static final String ID_ARRAY = "id[]";
    private final List<Long> reviewIds;

    public TranslateReviewsRequest(List<Long> reviewIds2) {
        this.reviewIds = reviewIds2;
        singleResponse();
    }

    public TranslateReviewsRequest(long reviewId) {
        this(Collections.singletonList(Long.valueOf(reviewId)));
    }

    public String getPath() {
        return "translated_reviews";
    }

    public Collection<Query> getQueryParams() {
        Collection<Query> queries = new ArrayList<>(this.reviewIds.size());
        for (Long longValue : this.reviewIds) {
            queries.add(new Query(ID_ARRAY, String.valueOf(longValue.longValue())));
        }
        return queries;
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public long getCacheOnlyTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public Type successResponseType() {
        return TranslateReviewsResponse.class;
    }
}
