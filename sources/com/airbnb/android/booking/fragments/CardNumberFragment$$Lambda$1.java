package com.airbnb.android.booking.fragments;

final /* synthetic */ class CardNumberFragment$$Lambda$1 implements Runnable {
    private final CardNumberFragment arg$1;

    private CardNumberFragment$$Lambda$1(CardNumberFragment cardNumberFragment) {
        this.arg$1 = cardNumberFragment;
    }

    public static Runnable lambdaFactory$(CardNumberFragment cardNumberFragment) {
        return new CardNumberFragment$$Lambda$1(cardNumberFragment);
    }

    public void run() {
        CardNumberFragment.lambda$setUpCardNumberInput$0(this.arg$1);
    }
}
