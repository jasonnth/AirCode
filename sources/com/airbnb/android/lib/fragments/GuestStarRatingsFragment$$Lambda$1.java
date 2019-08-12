package com.airbnb.android.lib.fragments;

import android.content.Context;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.lib.activities.HostReservationObjectActivity;
import com.airbnb.android.lib.adapters.GuestStarRatingsEpoxyController.GuestStarRatingsListener;
import com.airbnb.android.lib.fragments.reviews.ReservationReviewsFragment;

final /* synthetic */ class GuestStarRatingsFragment$$Lambda$1 implements GuestStarRatingsListener {
    private final GuestStarRatingsFragment arg$1;
    private final Context arg$2;

    private GuestStarRatingsFragment$$Lambda$1(GuestStarRatingsFragment guestStarRatingsFragment, Context context) {
        this.arg$1 = guestStarRatingsFragment;
        this.arg$2 = context;
    }

    public static GuestStarRatingsListener lambdaFactory$(GuestStarRatingsFragment guestStarRatingsFragment, Context context) {
        return new GuestStarRatingsFragment$$Lambda$1(guestStarRatingsFragment, context);
    }

    public void onSeeAllReviews() {
        ((HostReservationObjectActivity) this.arg$2).showFragmentInModal(ReservationReviewsFragment.newInstanceForUser(this.arg$1.guest, ReviewsMode.MODE_GUEST, true));
    }
}
