package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class BankTransferInfoFragment$$Lambda$1 implements Action1 {
    private final BankTransferInfoFragment arg$1;

    private BankTransferInfoFragment$$Lambda$1(BankTransferInfoFragment bankTransferInfoFragment) {
        this.arg$1 = bankTransferInfoFragment;
    }

    public static Action1 lambdaFactory$(BankTransferInfoFragment bankTransferInfoFragment) {
        return new BankTransferInfoFragment$$Lambda$1(bankTransferInfoFragment);
    }

    public void call(Object obj) {
        BankTransferInfoFragment.lambda$new$0(this.arg$1, (PaymentInstrumentResponse) obj);
    }
}
