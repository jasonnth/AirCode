package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.fragments.ReviewsFragment;

public class ReviewsActivity extends SolitAirActivity {
    private static final String ARG_LISTING = "listing";
    private static final String ARG_REVIEW_MODE = "reviewMode";
    private static final String ARG_USER = "user";
    protected static final String TAG = ReviewsActivity.class.getSimpleName();

    public static Intent intentForUser(Context context, User user, ReviewsMode mode) {
        Intent intent = new Intent(context, ReviewsActivity.class);
        intent.putExtra(ARG_REVIEW_MODE, mode.ordinal());
        intent.putExtra("user", user);
        return intent;
    }

    public void onCreate(Bundle savedInstanceState) {
        Fragment newInstanceForUser;
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        Listing listing = (Listing) intent.getParcelableExtra("listing");
        User user = listing == null ? (User) intent.getParcelableExtra("user") : listing.getHost();
        ReviewsMode reviewsMode = ReviewsMode.values()[intent.getIntExtra(ARG_REVIEW_MODE, ReviewsMode.MODE_ALL.ordinal())];
        if (user == null || (reviewsMode == ReviewsMode.MODE_LISTING && listing == null)) {
            throw new IllegalArgumentException("bad user or listing passed to reviews activity");
        } else if (savedInstanceState == null) {
            if (reviewsMode == ReviewsMode.MODE_LISTING) {
                newInstanceForUser = ReviewsFragment.newInstanceForListing(listing, reviewsMode);
            } else {
                newInstanceForUser = ReviewsFragment.newInstanceForUser(user, reviewsMode);
            }
            showFragment(newInstanceForUser, false);
        }
    }
}
