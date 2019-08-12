package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AppIntroFtueFragment$$Lambda$1 implements OnClickListener {
    private final AppIntroFtueFragment arg$1;

    private AppIntroFtueFragment$$Lambda$1(AppIntroFtueFragment appIntroFtueFragment) {
        this.arg$1 = appIntroFtueFragment;
    }

    public static OnClickListener lambdaFactory$(AppIntroFtueFragment appIntroFtueFragment) {
        return new AppIntroFtueFragment$$Lambda$1(appIntroFtueFragment);
    }

    public void onClick(View view) {
        AppIntroFtueFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
