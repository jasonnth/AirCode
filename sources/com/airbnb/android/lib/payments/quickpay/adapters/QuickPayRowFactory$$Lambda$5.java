package com.airbnb.android.lib.payments.quickpay.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.HomesQuickPayClickListener;

final /* synthetic */ class QuickPayRowFactory$$Lambda$5 implements OnClickListener {
    private final HomesQuickPayClickListener arg$1;

    private QuickPayRowFactory$$Lambda$5(HomesQuickPayClickListener homesQuickPayClickListener) {
        this.arg$1 = homesQuickPayClickListener;
    }

    public static OnClickListener lambdaFactory$(HomesQuickPayClickListener homesQuickPayClickListener) {
        return new QuickPayRowFactory$$Lambda$5(homesQuickPayClickListener);
    }

    public void onClick(View view) {
        this.arg$1.onGiftCreditToggled();
    }
}
