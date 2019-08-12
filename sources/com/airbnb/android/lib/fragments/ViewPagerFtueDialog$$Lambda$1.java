package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.views.ClickableViewPager;

final /* synthetic */ class ViewPagerFtueDialog$$Lambda$1 implements OnClickListener {
    private final ViewPagerFtueDialog arg$1;
    private final ClickableViewPager arg$2;

    private ViewPagerFtueDialog$$Lambda$1(ViewPagerFtueDialog viewPagerFtueDialog, ClickableViewPager clickableViewPager) {
        this.arg$1 = viewPagerFtueDialog;
        this.arg$2 = clickableViewPager;
    }

    public static OnClickListener lambdaFactory$(ViewPagerFtueDialog viewPagerFtueDialog, ClickableViewPager clickableViewPager) {
        return new ViewPagerFtueDialog$$Lambda$1(viewPagerFtueDialog, clickableViewPager);
    }

    public void onClick(View view) {
        ViewPagerFtueDialog.lambda$onCreateView$0(this.arg$1, this.arg$2, view);
    }
}
