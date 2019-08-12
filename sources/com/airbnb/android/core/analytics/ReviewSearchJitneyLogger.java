package com.airbnb.android.core.analytics;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.jitney.event.logging.ReviewSearch.p235v1.ReviewSearchClickReviewSuggestedKeywordEvent;
import com.airbnb.jitney.event.logging.ReviewSearch.p235v1.ReviewSearchClickSearchReviewsEvent.Builder;

public class ReviewSearchJitneyLogger extends BaseLogger {
    public ReviewSearchJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logClickReviewSearchMenuItem(long listingId) {
        publish(new Builder(context(), Long.valueOf(listingId)));
    }

    public void logClickReviewSearchKeyword(long listingId, String keyword) {
        publish(new ReviewSearchClickReviewSuggestedKeywordEvent.Builder(context(), Long.valueOf(listingId), keyword));
    }
}
