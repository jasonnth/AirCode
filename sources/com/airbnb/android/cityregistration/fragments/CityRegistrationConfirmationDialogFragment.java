package com.airbnb.android.cityregistration.fragments;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.core.fragments.AirDialogFragment;

public class CityRegistrationConfirmationDialogFragment extends AirDialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(1, C5630R.C5635style.ProgressDialog);
        return new Builder(getActivity()).setMessage(getString(C5630R.string.cityregistration_file_removal_confirmation)).setPositiveButton(C5630R.string.remove, CityRegistrationConfirmationDialogFragment$$Lambda$1.lambdaFactory$(this)).setNegativeButton(C5630R.string.cancel, CityRegistrationConfirmationDialogFragment$$Lambda$2.lambdaFactory$(this)).create();
    }

    static /* synthetic */ void lambda$onCreateDialog$0(CityRegistrationConfirmationDialogFragment cityRegistrationConfirmationDialogFragment, DialogInterface dialog, int which) {
        if (cityRegistrationConfirmationDialogFragment.getTargetFragment() != null) {
            cityRegistrationConfirmationDialogFragment.getTargetFragment().onActivityResult(cityRegistrationConfirmationDialogFragment.getTargetRequestCode(), -1, null);
        }
        dialog.dismiss();
    }

    static /* synthetic */ void lambda$onCreateDialog$1(CityRegistrationConfirmationDialogFragment cityRegistrationConfirmationDialogFragment, DialogInterface dialog, int which) {
        if (cityRegistrationConfirmationDialogFragment.getTargetFragment() != null) {
            cityRegistrationConfirmationDialogFragment.getTargetFragment().onActivityResult(cityRegistrationConfirmationDialogFragment.getTargetRequestCode(), 0, null);
        }
        dialog.dismiss();
    }
}
