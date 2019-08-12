package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AppIntroFtueActivity$$Lambda$3 implements OnClickListener {
    private final AppIntroFtueActivity arg$1;

    private AppIntroFtueActivity$$Lambda$3(AppIntroFtueActivity appIntroFtueActivity) {
        this.arg$1 = appIntroFtueActivity;
    }

    public static OnClickListener lambdaFactory$(AppIntroFtueActivity appIntroFtueActivity) {
        return new AppIntroFtueActivity$$Lambda$3(appIntroFtueActivity);
    }

    public void onClick(View view) {
        AppIntroFtueActivity.lambda$addActionBarSkipButton$2(this.arg$1, view);
    }
}
