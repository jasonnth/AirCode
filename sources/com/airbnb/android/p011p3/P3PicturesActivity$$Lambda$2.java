package com.airbnb.android.p011p3;

/* renamed from: com.airbnb.android.p3.P3PicturesActivity$$Lambda$2 */
final /* synthetic */ class P3PicturesActivity$$Lambda$2 implements Runnable {
    private final P3PicturesActivity arg$1;

    private P3PicturesActivity$$Lambda$2(P3PicturesActivity p3PicturesActivity) {
        this.arg$1 = p3PicturesActivity;
    }

    public static Runnable lambdaFactory$(P3PicturesActivity p3PicturesActivity) {
        return new P3PicturesActivity$$Lambda$2(p3PicturesActivity);
    }

    public void run() {
        this.arg$1.scheduleStartPostponedTransition();
    }
}
