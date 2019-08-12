package com.airbnb.android.listing.adapters;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class GuestAdditionalRequirementsAdapter$$Lambda$2 implements OnCheckedChangeListener {
    private final GuestAdditionalRequirementsAdapter arg$1;

    private GuestAdditionalRequirementsAdapter$$Lambda$2(GuestAdditionalRequirementsAdapter guestAdditionalRequirementsAdapter) {
        this.arg$1 = guestAdditionalRequirementsAdapter;
    }

    public static OnCheckedChangeListener lambdaFactory$(GuestAdditionalRequirementsAdapter guestAdditionalRequirementsAdapter) {
        return new GuestAdditionalRequirementsAdapter$$Lambda$2(guestAdditionalRequirementsAdapter);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.hostRecommendationChecked = z;
    }
}
