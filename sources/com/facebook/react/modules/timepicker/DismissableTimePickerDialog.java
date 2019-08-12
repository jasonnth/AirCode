package com.facebook.react.modules.timepicker;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.os.Build.VERSION;

public class DismissableTimePickerDialog extends TimePickerDialog {
    public DismissableTimePickerDialog(Context context, OnTimeSetListener callback, int hourOfDay, int minute, boolean is24HourView) {
        super(context, callback, hourOfDay, minute, is24HourView);
    }

    public DismissableTimePickerDialog(Context context, int theme, OnTimeSetListener callback, int hourOfDay, int minute, boolean is24HourView) {
        super(context, theme, callback, hourOfDay, minute, is24HourView);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (VERSION.SDK_INT > 19) {
            super.onStop();
        }
    }
}
