package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OnlineIdChildFragment$$Lambda$2 implements OnClickListener {
    private final OnlineIdChildFragment arg$1;

    private OnlineIdChildFragment$$Lambda$2(OnlineIdChildFragment onlineIdChildFragment) {
        this.arg$1 = onlineIdChildFragment;
    }

    public static OnClickListener lambdaFactory$(OnlineIdChildFragment onlineIdChildFragment) {
        return new OnlineIdChildFragment$$Lambda$2(onlineIdChildFragment);
    }

    public void onClick(View view) {
        ((OnlineIdFragment) this.arg$1.getParentFragment()).clickFacebookButton();
    }
}
