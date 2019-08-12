package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.views.ClickableViewPager;

final /* synthetic */ class ViewPagerFtueBaseActivity$$Lambda$2 implements OnClickListener {
    private final ViewPagerFtueBaseActivity arg$1;
    private final ClickableViewPager arg$2;

    private ViewPagerFtueBaseActivity$$Lambda$2(ViewPagerFtueBaseActivity viewPagerFtueBaseActivity, ClickableViewPager clickableViewPager) {
        this.arg$1 = viewPagerFtueBaseActivity;
        this.arg$2 = clickableViewPager;
    }

    public static OnClickListener lambdaFactory$(ViewPagerFtueBaseActivity viewPagerFtueBaseActivity, ClickableViewPager clickableViewPager) {
        return new ViewPagerFtueBaseActivity$$Lambda$2(viewPagerFtueBaseActivity, clickableViewPager);
    }

    public void onClick(View view) {
        ViewPagerFtueBaseActivity.lambda$onCreate$0(this.arg$1, this.arg$2, view);
    }
}
