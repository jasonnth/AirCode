package com.airbnb.android.listyourspacedls.fragments;

final /* synthetic */ class LYSBasePriceFragment$$Lambda$9 implements Runnable {
    private final LYSBasePriceFragment arg$1;
    private final boolean arg$2;

    private LYSBasePriceFragment$$Lambda$9(LYSBasePriceFragment lYSBasePriceFragment, boolean z) {
        this.arg$1 = lYSBasePriceFragment;
        this.arg$2 = z;
    }

    public static Runnable lambdaFactory$(LYSBasePriceFragment lYSBasePriceFragment, boolean z) {
        return new LYSBasePriceFragment$$Lambda$9(lYSBasePriceFragment, z);
    }

    public void run() {
        LYSBasePriceFragment.lambda$null$0(this.arg$1, this.arg$2);
    }
}
