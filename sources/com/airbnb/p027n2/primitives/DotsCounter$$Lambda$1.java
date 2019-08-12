package com.airbnb.p027n2.primitives;

/* renamed from: com.airbnb.n2.primitives.DotsCounter$$Lambda$1 */
final /* synthetic */ class DotsCounter$$Lambda$1 implements Runnable {
    private final DotsCounter arg$1;

    private DotsCounter$$Lambda$1(DotsCounter dotsCounter) {
        this.arg$1 = dotsCounter;
    }

    public static Runnable lambdaFactory$(DotsCounter dotsCounter) {
        return new DotsCounter$$Lambda$1(dotsCounter);
    }

    public void run() {
        this.arg$1.setupDots();
    }
}
