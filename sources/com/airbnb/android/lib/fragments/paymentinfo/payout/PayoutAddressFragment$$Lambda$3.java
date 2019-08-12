package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PayoutAddressFragment$$Lambda$3 implements OnClickListener {
    private final PayoutAddressFragment arg$1;

    private PayoutAddressFragment$$Lambda$3(PayoutAddressFragment payoutAddressFragment) {
        this.arg$1 = payoutAddressFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutAddressFragment payoutAddressFragment) {
        return new PayoutAddressFragment$$Lambda$3(payoutAddressFragment);
    }

    public void onClick(View view) {
        this.arg$1.getNavigationController().onSelectCountry(this.arg$1.countryCode);
    }
}
