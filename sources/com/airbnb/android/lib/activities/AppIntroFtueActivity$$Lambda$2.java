package com.airbnb.android.lib.activities;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AppIntroFtueActivity$$Lambda$2 implements OnClickListener {
    private final AppIntroFtueActivity arg$1;
    private final Intent arg$2;

    private AppIntroFtueActivity$$Lambda$2(AppIntroFtueActivity appIntroFtueActivity, Intent intent) {
        this.arg$1 = appIntroFtueActivity;
        this.arg$2 = intent;
    }

    public static OnClickListener lambdaFactory$(AppIntroFtueActivity appIntroFtueActivity, Intent intent) {
        return new AppIntroFtueActivity$$Lambda$2(appIntroFtueActivity, intent);
    }

    public void onClick(View view) {
        AppIntroFtueActivity.lambda$onCreate$1(this.arg$1, this.arg$2, view);
    }
}