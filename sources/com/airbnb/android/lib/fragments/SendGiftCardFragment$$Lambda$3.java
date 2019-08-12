package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.CreateGiftCardResponse;
import p032rx.functions.Action1;

final /* synthetic */ class SendGiftCardFragment$$Lambda$3 implements Action1 {
    private final SendGiftCardFragment arg$1;

    private SendGiftCardFragment$$Lambda$3(SendGiftCardFragment sendGiftCardFragment) {
        this.arg$1 = sendGiftCardFragment;
    }

    public static Action1 lambdaFactory$(SendGiftCardFragment sendGiftCardFragment) {
        return new SendGiftCardFragment$$Lambda$3(sendGiftCardFragment);
    }

    public void call(Object obj) {
        SendGiftCardFragment.lambda$new$4(this.arg$1, (CreateGiftCardResponse) obj);
    }
}
