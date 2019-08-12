package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OnlineIdChildFragment$$Lambda$5 implements OnClickListener {
    private final OnlineIdChildFragment arg$1;

    private OnlineIdChildFragment$$Lambda$5(OnlineIdChildFragment onlineIdChildFragment) {
        this.arg$1 = onlineIdChildFragment;
    }

    public static OnClickListener lambdaFactory$(OnlineIdChildFragment onlineIdChildFragment) {
        return new OnlineIdChildFragment$$Lambda$5(onlineIdChildFragment);
    }

    public void onClick(View view) {
        ((OnlineIdFragment) this.arg$1.getParentFragment()).clickWeiboButton();
    }
}
