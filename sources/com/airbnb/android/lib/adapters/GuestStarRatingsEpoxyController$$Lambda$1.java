package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GuestStarRatingsEpoxyController$$Lambda$1 implements OnClickListener {
    private final GuestStarRatingsEpoxyController arg$1;

    private GuestStarRatingsEpoxyController$$Lambda$1(GuestStarRatingsEpoxyController guestStarRatingsEpoxyController) {
        this.arg$1 = guestStarRatingsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(GuestStarRatingsEpoxyController guestStarRatingsEpoxyController) {
        return new GuestStarRatingsEpoxyController$$Lambda$1(guestStarRatingsEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onSeeAllReviews();
    }
}
