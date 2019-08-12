package com.airbnb.android.p011p3;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchFragment$$Lambda$7 */
final /* synthetic */ class P3ReviewSearchFragment$$Lambda$7 implements Runnable {
    private final P3ReviewSearchFragment arg$1;

    private P3ReviewSearchFragment$$Lambda$7(P3ReviewSearchFragment p3ReviewSearchFragment) {
        this.arg$1 = p3ReviewSearchFragment;
    }

    public static Runnable lambdaFactory$(P3ReviewSearchFragment p3ReviewSearchFragment) {
        return new P3ReviewSearchFragment$$Lambda$7(p3ReviewSearchFragment);
    }

    public void run() {
        P3ReviewSearchFragment.lambda$setupInputMarquee$6(this.arg$1);
    }
}
