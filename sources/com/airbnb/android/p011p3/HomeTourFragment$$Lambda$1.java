package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.p3.HomeTourFragment$$Lambda$1 */
final /* synthetic */ class HomeTourFragment$$Lambda$1 implements OnClickListener {
    private final HomeTourFragment arg$1;

    private HomeTourFragment$$Lambda$1(HomeTourFragment homeTourFragment) {
        this.arg$1 = homeTourFragment;
    }

    public static OnClickListener lambdaFactory$(HomeTourFragment homeTourFragment) {
        return new HomeTourFragment$$Lambda$1(homeTourFragment);
    }

    public void onClick(View view) {
        this.arg$1.getAirActivity().onBackPressed();
    }
}
