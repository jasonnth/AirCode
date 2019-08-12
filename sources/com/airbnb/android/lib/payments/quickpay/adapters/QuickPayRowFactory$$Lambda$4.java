package com.airbnb.android.lib.payments.quickpay.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.PaidAmenityQuickPayClickListener;

final /* synthetic */ class QuickPayRowFactory$$Lambda$4 implements OnClickListener {
    private final PaidAmenityQuickPayClickListener arg$1;

    private QuickPayRowFactory$$Lambda$4(PaidAmenityQuickPayClickListener paidAmenityQuickPayClickListener) {
        this.arg$1 = paidAmenityQuickPayClickListener;
    }

    public static OnClickListener lambdaFactory$(PaidAmenityQuickPayClickListener paidAmenityQuickPayClickListener) {
        return new QuickPayRowFactory$$Lambda$4(paidAmenityQuickPayClickListener);
    }

    public void onClick(View view) {
        this.arg$1.onGiftCreditToggled();
    }
}
