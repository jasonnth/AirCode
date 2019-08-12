package com.airbnb.android.lib.activities.reviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.ReviewsAnalytics;
import com.airbnb.android.lib.fragments.reviews.ReviewSummaryFragment;

public class ReviewSummaryActivity extends SolitAirActivity {
    private static final String KEY_REVIEW = "review";

    public static Intent intentForReview(Context context, Review review) {
        Intent intent = new Intent(context, ReviewSummaryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("review", review);
        intent.putExtras(bundle);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Review review = (Review) getIntent().getParcelableExtra("review");
        setupActionBar(C0880R.string.review_summary_title, new Object[0]);
        showFragment(ReviewSummaryFragment.newInstance(review), false);
        ReviewsAnalytics.trackSummarySection(review);
    }
}
