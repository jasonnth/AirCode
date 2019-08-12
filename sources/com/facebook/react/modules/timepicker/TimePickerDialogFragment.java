package com.facebook.react.modules.timepicker;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import com.airbnb.android.core.models.CohostInvitation;
import java.util.Calendar;

public class TimePickerDialogFragment extends DialogFragment {
    private OnDismissListener mOnDismissListener;
    private OnTimeSetListener mOnTimeSetListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialog(getArguments(), getActivity(), this.mOnTimeSetListener);
    }

    static Dialog createDialog(Bundle args, Context activityContext, OnTimeSetListener onTimeSetListener) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(11);
        int minute = now.get(12);
        boolean is24hour = DateFormat.is24HourFormat(activityContext);
        if (args != null) {
            hour = args.getInt(CohostInvitation.HOUR, now.get(11));
            minute = args.getInt(CohostInvitation.MINUTE, now.get(12));
            is24hour = args.getBoolean("is24Hour", DateFormat.is24HourFormat(activityContext));
        }
        return new DismissableTimePickerDialog(activityContext, onTimeSetListener, hour, minute, is24hour);
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss(dialog);
        }
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setOnTimeSetListener(OnTimeSetListener onTimeSetListener) {
        this.mOnTimeSetListener = onTimeSetListener;
    }
}
