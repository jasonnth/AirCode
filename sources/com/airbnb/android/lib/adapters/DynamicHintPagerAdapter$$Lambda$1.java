package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DynamicHintPagerAdapter$$Lambda$1 implements OnClickListener {
    private final OnClickListener arg$1;

    private DynamicHintPagerAdapter$$Lambda$1(OnClickListener onClickListener) {
        this.arg$1 = onClickListener;
    }

    public static OnClickListener lambdaFactory$(OnClickListener onClickListener) {
        return new DynamicHintPagerAdapter$$Lambda$1(onClickListener);
    }

    public void onClick(View view) {
        this.arg$1.onClick(view);
    }
}
