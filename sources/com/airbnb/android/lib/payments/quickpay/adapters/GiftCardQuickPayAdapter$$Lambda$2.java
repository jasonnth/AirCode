package com.airbnb.android.lib.payments.quickpay.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GiftCardQuickPayAdapter$$Lambda$2 implements OnClickListener {
    private final GiftCardQuickPayAdapter arg$1;

    private GiftCardQuickPayAdapter$$Lambda$2(GiftCardQuickPayAdapter giftCardQuickPayAdapter) {
        this.arg$1 = giftCardQuickPayAdapter;
    }

    public static OnClickListener lambdaFactory$(GiftCardQuickPayAdapter giftCardQuickPayAdapter) {
        return new GiftCardQuickPayAdapter$$Lambda$2(giftCardQuickPayAdapter);
    }

    public void onClick(View view) {
        this.arg$1.clickListener.onPaymentRowClicked();
    }
}
