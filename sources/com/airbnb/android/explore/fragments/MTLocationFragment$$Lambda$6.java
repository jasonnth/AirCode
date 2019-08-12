package com.airbnb.android.explore.fragments;

final /* synthetic */ class MTLocationFragment$$Lambda$6 implements Runnable {
    private final MTLocationFragment arg$1;
    private final boolean arg$2;
    private final String arg$3;

    private MTLocationFragment$$Lambda$6(MTLocationFragment mTLocationFragment, boolean z, String str) {
        this.arg$1 = mTLocationFragment;
        this.arg$2 = z;
        this.arg$3 = str;
    }

    public static Runnable lambdaFactory$(MTLocationFragment mTLocationFragment, boolean z, String str) {
        return new MTLocationFragment$$Lambda$6(mTLocationFragment, z, str);
    }

    public void run() {
        MTLocationFragment.lambda$onSearchQueryChanged$1(this.arg$1, this.arg$2, this.arg$3);
    }
}
