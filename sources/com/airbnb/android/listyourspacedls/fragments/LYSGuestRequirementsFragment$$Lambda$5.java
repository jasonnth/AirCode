package com.airbnb.android.listyourspacedls.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class LYSGuestRequirementsFragment$$Lambda$5 implements OnClickListener {
    private static final LYSGuestRequirementsFragment$$Lambda$5 instance = new LYSGuestRequirementsFragment$$Lambda$5();

    private LYSGuestRequirementsFragment$$Lambda$5() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        LYSGuestRequirementsFragment.lambda$addAdditionalRequirements$4(dialogInterface, i);
    }
}
