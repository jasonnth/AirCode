package com.airbnb.android.booking.fragments.alipay;

final /* synthetic */ class AlipayIdFragment$$Lambda$1 implements Runnable {
    private final AlipayIdFragment arg$1;

    private AlipayIdFragment$$Lambda$1(AlipayIdFragment alipayIdFragment) {
        this.arg$1 = alipayIdFragment;
    }

    public static Runnable lambdaFactory$(AlipayIdFragment alipayIdFragment) {
        return new AlipayIdFragment$$Lambda$1(alipayIdFragment);
    }

    public void run() {
        AlipayIdFragment.lambda$onCreateView$0(this.arg$1);
    }
}
