package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.responses.ReviewsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ReviewsRequest extends BaseRequestV2<ReviewsResponse> {
    public static final int DEFAULT_LIMIT = 20;
    protected static final String QUERY_KEY_LISTING_ID = "listing_id";
    protected static final String QUERY_KEY_REVIEWEE_ID = "reviewee_id";
    private final boolean forCurrentUser;
    private final int limit;
    private final int offset;
    private final String queryKey;
    private final long queryValue;
    private final ReviewsFrom reviewsFrom;

    public enum ReviewsFrom {
        GUESTS(TripRole.ROLE_KEY_GUEST),
        HOSTS(TripRole.ROLE_KEY_HOST),
        ALL("all");
        
        /* access modifiers changed from: private */
        public final String apiString;

        private ReviewsFrom(String apiString2) {
            this.apiString = apiString2;
        }

        public static ReviewsFrom transformFrom(ReviewsMode mode) {
            switch (mode) {
                case MODE_HOST:
                    return GUESTS;
                case MODE_GUEST:
                    return HOSTS;
                default:
                    return ALL;
            }
        }
    }

    public static ReviewsRequest forReviewCount(AirbnbAccountManager accountManager, long userId, ReviewsFrom role) {
        return forUser(accountManager, userId, role, 0, 0);
    }

    public static ReviewsRequest forUser(AirbnbAccountManager accountManager, long userId, ReviewsFrom role, int offset2, int limit2) {
        return new ReviewsRequest(accountManager.isCurrentUser(userId), QUERY_KEY_REVIEWEE_ID, userId, role, offset2, limit2);
    }

    public static ReviewsRequest forListing(long listingId, int offset2) {
        return new ReviewsRequest(false, "listing_id", listingId, ReviewsFrom.ALL, offset2, 20);
    }

    private ReviewsRequest(boolean forCurrentUser2, String queryKey2, long queryValue2, ReviewsFrom reviewsFrom2, int offset2, int limit2) {
        this.forCurrentUser = forCurrentUser2;
        this.queryKey = queryKey2;
        this.queryValue = queryValue2;
        this.reviewsFrom = reviewsFrom2;
        this.offset = offset2;
        this.limit = limit2;
    }

    public String getPath() {
        return VerificationsAdapter.VERIFICATION_REVIEWS;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv(this.queryKey, this.queryValue).mo7895kv("role", this.reviewsFrom.apiString).mo7893kv(TimelineRequest.ARG_OFFSET, this.offset).mo7893kv(TimelineRequest.ARG_LIMIT, this.limit).mo7895kv("_order", "language_country").mo7895kv(TimelineRequest.ARG_FORMAT, this.forCurrentUser ? "for_mobile_client_with_private_feedback" : "for_mobile_client");
    }

    public int getOffset() {
        return this.offset;
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public long getCacheOnlyTimeoutMs() {
        if ("listing_id".equals(this.queryKey)) {
            return 86400000;
        }
        return 600000;
    }

    public Type successResponseType() {
        return ReviewsResponse.class;
    }
}
