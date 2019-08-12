package com.airbnb.android.lib.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class GenderSelectionFragment$$Lambda$1 implements OnClickListener {
    private final GenderSelectionFragment arg$1;

    private GenderSelectionFragment$$Lambda$1(GenderSelectionFragment genderSelectionFragment) {
        this.arg$1 = genderSelectionFragment;
    }

    public static OnClickListener lambdaFactory$(GenderSelectionFragment genderSelectionFragment) {
        return new GenderSelectionFragment$$Lambda$1(genderSelectionFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        GenderSelectionFragment.lambda$onCreateDialog$0(this.arg$1, dialogInterface, i);
    }
}
