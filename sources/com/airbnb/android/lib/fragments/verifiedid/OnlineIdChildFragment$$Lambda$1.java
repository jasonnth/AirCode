package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OnlineIdChildFragment$$Lambda$1 implements OnClickListener {
    private final OnlineIdChildFragment arg$1;

    private OnlineIdChildFragment$$Lambda$1(OnlineIdChildFragment onlineIdChildFragment) {
        this.arg$1 = onlineIdChildFragment;
    }

    public static OnClickListener lambdaFactory$(OnlineIdChildFragment onlineIdChildFragment) {
        return new OnlineIdChildFragment$$Lambda$1(onlineIdChildFragment);
    }

    public void onClick(View view) {
        OnlineIdChildFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
