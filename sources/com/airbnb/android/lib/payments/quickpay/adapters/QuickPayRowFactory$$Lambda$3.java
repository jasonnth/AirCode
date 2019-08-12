package com.airbnb.android.lib.payments.quickpay.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.MagicalTripsQuickPayClickListener;

final /* synthetic */ class QuickPayRowFactory$$Lambda$3 implements OnClickListener {
    private final MagicalTripsQuickPayClickListener arg$1;

    private QuickPayRowFactory$$Lambda$3(MagicalTripsQuickPayClickListener magicalTripsQuickPayClickListener) {
        this.arg$1 = magicalTripsQuickPayClickListener;
    }

    public static OnClickListener lambdaFactory$(MagicalTripsQuickPayClickListener magicalTripsQuickPayClickListener) {
        return new QuickPayRowFactory$$Lambda$3(magicalTripsQuickPayClickListener);
    }

    public void onClick(View view) {
        this.arg$1.onGiftCreditToggled();
    }
}
