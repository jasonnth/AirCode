package com.airbnb.android.photopicker;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;

public class ProcessingDialogFragment extends DialogFragment {
    public static ProcessingDialogFragment newInstance() {
        return new ProcessingDialogFragment();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return ProgressDialog.show(getActivity(), getString(C7593R.string.photo_picker_dialog_title_processing), getString(C7593R.string.photo_picker_dialog_message_processing), true);
    }
}
