package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class SendGiftCardFragment$$Lambda$4 implements Action1 {
    private final SendGiftCardFragment arg$1;

    private SendGiftCardFragment$$Lambda$4(SendGiftCardFragment sendGiftCardFragment) {
        this.arg$1 = sendGiftCardFragment;
    }

    public static Action1 lambdaFactory$(SendGiftCardFragment sendGiftCardFragment) {
        return new SendGiftCardFragment$$Lambda$4(sendGiftCardFragment);
    }

    public void call(Object obj) {
        SendGiftCardFragment.lambda$new$5(this.arg$1, (AirRequestNetworkException) obj);
    }
}
