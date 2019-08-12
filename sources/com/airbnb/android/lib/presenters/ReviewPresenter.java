package com.airbnb.android.lib.presenters;

import com.airbnb.android.core.models.Review;

public class ReviewPresenter {
    public static boolean shouldShowListing(Review review) {
        return review.hasListingInfo() && review.isGuestReviewingHost();
    }
}
