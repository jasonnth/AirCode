package com.airbnb.android.listing.adapters;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class NightlyPriceAdapter$$Lambda$1 implements OnCheckedChangeListener {
    private final NightlyPriceAdapter arg$1;

    private NightlyPriceAdapter$$Lambda$1(NightlyPriceAdapter nightlyPriceAdapter) {
        this.arg$1 = nightlyPriceAdapter;
    }

    public static OnCheckedChangeListener lambdaFactory$(NightlyPriceAdapter nightlyPriceAdapter) {
        return new NightlyPriceAdapter$$Lambda$1(nightlyPriceAdapter);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        NightlyPriceAdapter.lambda$new$0(this.arg$1, switchRowInterface, z);
    }
}
