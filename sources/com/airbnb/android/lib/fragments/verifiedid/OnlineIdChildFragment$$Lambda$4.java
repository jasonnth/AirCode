package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OnlineIdChildFragment$$Lambda$4 implements OnClickListener {
    private final OnlineIdChildFragment arg$1;

    private OnlineIdChildFragment$$Lambda$4(OnlineIdChildFragment onlineIdChildFragment) {
        this.arg$1 = onlineIdChildFragment;
    }

    public static OnClickListener lambdaFactory$(OnlineIdChildFragment onlineIdChildFragment) {
        return new OnlineIdChildFragment$$Lambda$4(onlineIdChildFragment);
    }

    public void onClick(View view) {
        ((OnlineIdFragment) this.arg$1.getParentFragment()).clickGoogleButton();
    }
}
