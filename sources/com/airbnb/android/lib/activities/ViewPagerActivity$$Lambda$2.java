package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ViewPagerActivity$$Lambda$2 implements OnClickListener {
    private final ViewPagerActivity arg$1;

    private ViewPagerActivity$$Lambda$2(ViewPagerActivity viewPagerActivity) {
        this.arg$1 = viewPagerActivity;
    }

    public static OnClickListener lambdaFactory$(ViewPagerActivity viewPagerActivity) {
        return new ViewPagerActivity$$Lambda$2(viewPagerActivity);
    }

    public void onClick(View view) {
        this.arg$1.onSecondaryClicked();
    }
}
