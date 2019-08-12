package com.airbnb.android.core.events;

import com.airbnb.android.core.models.Review;

public class ReviewUpdatedEvent {
    public final Review review;

    public ReviewUpdatedEvent(Review review2) {
        this.review = review2;
    }
}
