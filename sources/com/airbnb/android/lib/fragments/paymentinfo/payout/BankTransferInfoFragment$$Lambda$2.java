package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class BankTransferInfoFragment$$Lambda$2 implements Action1 {
    private final BankTransferInfoFragment arg$1;

    private BankTransferInfoFragment$$Lambda$2(BankTransferInfoFragment bankTransferInfoFragment) {
        this.arg$1 = bankTransferInfoFragment;
    }

    public static Action1 lambdaFactory$(BankTransferInfoFragment bankTransferInfoFragment) {
        return new BankTransferInfoFragment$$Lambda$2(bankTransferInfoFragment);
    }

    public void call(Object obj) {
        BankTransferInfoFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
