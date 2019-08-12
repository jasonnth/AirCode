package com.airbnb.android.lib.activities.reviews;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.User;

final /* synthetic */ class ReviewRatingsActivity$$Lambda$1 implements OnClickListener {
    private final ReviewRatingsActivity arg$1;
    private final User arg$2;

    private ReviewRatingsActivity$$Lambda$1(ReviewRatingsActivity reviewRatingsActivity, User user) {
        this.arg$1 = reviewRatingsActivity;
        this.arg$2 = user;
    }

    public static OnClickListener lambdaFactory$(ReviewRatingsActivity reviewRatingsActivity, User user) {
        return new ReviewRatingsActivity$$Lambda$1(reviewRatingsActivity, user);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(UserProfileIntents.intentForUserId(this.arg$1, this.arg$2.getId()));
    }
}
