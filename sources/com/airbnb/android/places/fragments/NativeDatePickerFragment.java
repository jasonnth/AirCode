package com.airbnb.android.places.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import java.util.Calendar;

public class NativeDatePickerFragment extends DialogFragment {
    private OnDateSetListener onDateSetListener;

    public static NativeDatePickerFragment newInstance() {
        return new NativeDatePickerFragment();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        return new DatePickerDialog(getActivity(), this.onDateSetListener, c.get(1), c.get(2), c.get(5));
    }

    public void setListener(OnDateSetListener onDateSetListener2) {
        this.onDateSetListener = onDateSetListener2;
    }
}
