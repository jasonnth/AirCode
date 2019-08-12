package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.GetGiftCreditTemplatesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class SendGiftCardFragment$$Lambda$1 implements Action1 {
    private final SendGiftCardFragment arg$1;

    private SendGiftCardFragment$$Lambda$1(SendGiftCardFragment sendGiftCardFragment) {
        this.arg$1 = sendGiftCardFragment;
    }

    public static Action1 lambdaFactory$(SendGiftCardFragment sendGiftCardFragment) {
        return new SendGiftCardFragment$$Lambda$1(sendGiftCardFragment);
    }

    public void call(Object obj) {
        SendGiftCardFragment.lambda$new$1(this.arg$1, (GetGiftCreditTemplatesResponse) obj);
    }
}
