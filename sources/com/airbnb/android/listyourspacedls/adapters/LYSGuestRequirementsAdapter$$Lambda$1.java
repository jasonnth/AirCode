package com.airbnb.android.listyourspacedls.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSGuestRequirementsAdapter$$Lambda$1 implements OnClickListener {
    private final LYSGuestRequirementsAdapter arg$1;

    private LYSGuestRequirementsAdapter$$Lambda$1(LYSGuestRequirementsAdapter lYSGuestRequirementsAdapter) {
        this.arg$1 = lYSGuestRequirementsAdapter;
    }

    public static OnClickListener lambdaFactory$(LYSGuestRequirementsAdapter lYSGuestRequirementsAdapter) {
        return new LYSGuestRequirementsAdapter$$Lambda$1(lYSGuestRequirementsAdapter);
    }

    public void onClick(View view) {
        this.arg$1.controller.addAdditionalRequirements();
    }
}
