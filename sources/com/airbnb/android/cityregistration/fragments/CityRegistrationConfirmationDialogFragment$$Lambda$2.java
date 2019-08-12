package com.airbnb.android.cityregistration.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class CityRegistrationConfirmationDialogFragment$$Lambda$2 implements OnClickListener {
    private final CityRegistrationConfirmationDialogFragment arg$1;

    private CityRegistrationConfirmationDialogFragment$$Lambda$2(CityRegistrationConfirmationDialogFragment cityRegistrationConfirmationDialogFragment) {
        this.arg$1 = cityRegistrationConfirmationDialogFragment;
    }

    public static OnClickListener lambdaFactory$(CityRegistrationConfirmationDialogFragment cityRegistrationConfirmationDialogFragment) {
        return new CityRegistrationConfirmationDialogFragment$$Lambda$2(cityRegistrationConfirmationDialogFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        CityRegistrationConfirmationDialogFragment.lambda$onCreateDialog$1(this.arg$1, dialogInterface, i);
    }
}
