package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WelcomeScreenFragment$$Lambda$2 implements OnClickListener {
    private final WelcomeScreenFragment arg$1;

    private WelcomeScreenFragment$$Lambda$2(WelcomeScreenFragment welcomeScreenFragment) {
        this.arg$1 = welcomeScreenFragment;
    }

    public static OnClickListener lambdaFactory$(WelcomeScreenFragment welcomeScreenFragment) {
        return new WelcomeScreenFragment$$Lambda$2(welcomeScreenFragment);
    }

    public void onClick(View view) {
        WelcomeScreenFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}
