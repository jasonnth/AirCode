package com.airbnb.android.explore.fragments;

final /* synthetic */ class MTLocationChinaFragment$$Lambda$9 implements Runnable {
    private final MTLocationChinaFragment arg$1;
    private final String arg$2;

    private MTLocationChinaFragment$$Lambda$9(MTLocationChinaFragment mTLocationChinaFragment, String str) {
        this.arg$1 = mTLocationChinaFragment;
        this.arg$2 = str;
    }

    public static Runnable lambdaFactory$(MTLocationChinaFragment mTLocationChinaFragment, String str) {
        return new MTLocationChinaFragment$$Lambda$9(mTLocationChinaFragment, str);
    }

    public void run() {
        MTLocationChinaFragment.lambda$onSearchQueryChanged$2(this.arg$1, this.arg$2);
    }
}
