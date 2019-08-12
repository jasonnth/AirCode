package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$4 implements OnClickListener {
    private final HostReviewDetailsFragment arg$1;

    private HostReviewDetailsFragment$$Lambda$4(HostReviewDetailsFragment hostReviewDetailsFragment) {
        this.arg$1 = hostReviewDetailsFragment;
    }

    public static OnClickListener lambdaFactory$(HostReviewDetailsFragment hostReviewDetailsFragment) {
        return new HostReviewDetailsFragment$$Lambda$4(hostReviewDetailsFragment);
    }

    public void onClick(View view) {
        HostReviewDetailsFragment.lambda$setupListingSelector$1(this.arg$1, view);
    }
}
