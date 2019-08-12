package com.airbnb.android.lib.views;

final /* synthetic */ class EllipsizingTextView$$Lambda$1 implements Runnable {
    private final EllipsizingTextView arg$1;

    private EllipsizingTextView$$Lambda$1(EllipsizingTextView ellipsizingTextView) {
        this.arg$1 = ellipsizingTextView;
    }

    public static Runnable lambdaFactory$(EllipsizingTextView ellipsizingTextView) {
        return new EllipsizingTextView$$Lambda$1(ellipsizingTextView);
    }

    public void run() {
        EllipsizingTextView.lambda$ellipsizeText$0(this.arg$1);
    }
}
