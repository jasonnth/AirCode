package com.airbnb.android.lib.fragments;

import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;

final /* synthetic */ class SendGiftCardFragment$$Lambda$6 implements StringTextWatcher {
    private final SendGiftCardFragment arg$1;

    private SendGiftCardFragment$$Lambda$6(SendGiftCardFragment sendGiftCardFragment) {
        this.arg$1 = sendGiftCardFragment;
    }

    public static StringTextWatcher lambdaFactory$(SendGiftCardFragment sendGiftCardFragment) {
        return new SendGiftCardFragment$$Lambda$6(sendGiftCardFragment);
    }

    public void textUpdated(String str) {
        SendGiftCardFragment.lambda$initializeGiftCreditAmountSelectorGroupedCell$3(this.arg$1, str);
    }
}
