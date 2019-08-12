package com.airbnb.android.lib.activities;

final /* synthetic */ class ViewPagerFtueBaseActivity$$Lambda$1 implements Runnable {
    private final ViewPagerFtueBaseActivity arg$1;

    private ViewPagerFtueBaseActivity$$Lambda$1(ViewPagerFtueBaseActivity viewPagerFtueBaseActivity) {
        this.arg$1 = viewPagerFtueBaseActivity;
    }

    public static Runnable lambdaFactory$(ViewPagerFtueBaseActivity viewPagerFtueBaseActivity) {
        return new ViewPagerFtueBaseActivity$$Lambda$1(viewPagerFtueBaseActivity);
    }

    public void run() {
        this.arg$1.mImageBg.setImageDrawable(this.arg$1.mCurrDrawable);
    }
}
