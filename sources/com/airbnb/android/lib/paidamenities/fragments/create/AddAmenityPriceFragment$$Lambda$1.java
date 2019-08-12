package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class AddAmenityPriceFragment$$Lambda$1 implements OnCheckedChangeListener {
    private final AddAmenityPriceFragment arg$1;

    private AddAmenityPriceFragment$$Lambda$1(AddAmenityPriceFragment addAmenityPriceFragment) {
        this.arg$1 = addAmenityPriceFragment;
    }

    public static OnCheckedChangeListener lambdaFactory$(AddAmenityPriceFragment addAmenityPriceFragment) {
        return new AddAmenityPriceFragment$$Lambda$1(addAmenityPriceFragment);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        AddAmenityPriceFragment.lambda$onCreateView$0(this.arg$1, switchRowInterface, z);
    }
}
