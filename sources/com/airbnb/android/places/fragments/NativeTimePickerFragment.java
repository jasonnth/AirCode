package com.airbnb.android.places.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import java.util.Calendar;

public class NativeTimePickerFragment extends DialogFragment {
    private OnTimeSetListener onTimeSetListener;

    public static NativeTimePickerFragment newInstance() {
        return new NativeTimePickerFragment();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        return new TimePickerDialog(getActivity(), this.onTimeSetListener, c.get(11), c.get(12), false);
    }

    public void setListener(OnTimeSetListener onTimeSetListener2) {
        this.onTimeSetListener = onTimeSetListener2;
    }
}
