package com.airbnb.android.lib.views;

import android.support.p000v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.lib.C0880R;

final /* synthetic */ class PricingQuotePricingDetails$$Lambda$1 implements OnClickListener {
    private final PricingQuotePricingDetails arg$1;

    private PricingQuotePricingDetails$$Lambda$1(PricingQuotePricingDetails pricingQuotePricingDetails) {
        this.arg$1 = pricingQuotePricingDetails;
    }

    public static OnClickListener lambdaFactory$(PricingQuotePricingDetails pricingQuotePricingDetails) {
        return new PricingQuotePricingDetails$$Lambda$1(pricingQuotePricingDetails);
    }

    public void onClick(View view) {
        this.arg$1.mListener.showDialog(ZenDialog.builder().withTitle(C0880R.string.price_service_fee).withBodyText(C0880R.string.price_service_fee_hint).withSingleButton(C0880R.string.okay, 0, (Fragment) null).create());
    }
}
