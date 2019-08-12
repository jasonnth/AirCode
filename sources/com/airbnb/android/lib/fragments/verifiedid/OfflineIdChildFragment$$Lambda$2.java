package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OfflineIdChildFragment$$Lambda$2 implements OnClickListener {
    private final OfflineIdChildFragment arg$1;

    private OfflineIdChildFragment$$Lambda$2(OfflineIdChildFragment offlineIdChildFragment) {
        this.arg$1 = offlineIdChildFragment;
    }

    public static OnClickListener lambdaFactory$(OfflineIdChildFragment offlineIdChildFragment) {
        return new OfflineIdChildFragment$$Lambda$2(offlineIdChildFragment);
    }

    public void onClick(View view) {
        OfflineIdChildFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}
