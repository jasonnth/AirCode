package com.airbnb.android.listyourspacedls.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class LYSGuestRequirementsFragment$$Lambda$4 implements OnClickListener {
    private final LYSGuestRequirementsFragment arg$1;

    private LYSGuestRequirementsFragment$$Lambda$4(LYSGuestRequirementsFragment lYSGuestRequirementsFragment) {
        this.arg$1 = lYSGuestRequirementsFragment;
    }

    public static OnClickListener lambdaFactory$(LYSGuestRequirementsFragment lYSGuestRequirementsFragment) {
        return new LYSGuestRequirementsFragment$$Lambda$4(lYSGuestRequirementsFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        LYSGuestRequirementsFragment.lambda$addAdditionalRequirements$3(this.arg$1, dialogInterface, i);
    }
}
