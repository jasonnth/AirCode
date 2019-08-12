package com.facebook.react.modules.datepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.DatePicker;
import com.facebook.internal.AnalyticsEvents;
import java.util.Calendar;
import java.util.Locale;

@SuppressLint({"ValidFragment"})
public class DatePickerDialogFragment extends DialogFragment {
    private static final long DEFAULT_MIN_DATE = -2208988800001L;
    private OnDateSetListener mOnDateSetListener;
    private OnDismissListener mOnDismissListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialog(getArguments(), getActivity(), this.mOnDateSetListener);
    }

    static Dialog createDialog(Bundle args, Context activityContext, OnDateSetListener onDateSetListener) {
        Calendar c = Calendar.getInstance();
        if (args != null && args.containsKey("date")) {
            c.setTimeInMillis(args.getLong("date"));
        }
        int year = c.get(1);
        int month = c.get(2);
        int day = c.get(5);
        DatePickerMode mode = DatePickerMode.DEFAULT;
        if (!(args == null || args.getString("mode", null) == null)) {
            mode = DatePickerMode.valueOf(args.getString("mode").toUpperCase(Locale.US));
        }
        DismissableDatePickerDialog dismissableDatePickerDialog = null;
        if (VERSION.SDK_INT < 21) {
            dismissableDatePickerDialog = new DismissableDatePickerDialog(activityContext, onDateSetListener, year, month, day);
            switch (mode) {
                case CALENDAR:
                    dismissableDatePickerDialog.getDatePicker().setCalendarViewShown(true);
                    dismissableDatePickerDialog.getDatePicker().setSpinnersShown(false);
                    break;
                case SPINNER:
                    dismissableDatePickerDialog.getDatePicker().setCalendarViewShown(false);
                    break;
            }
        } else {
            switch (mode) {
                case CALENDAR:
                    dismissableDatePickerDialog = new DismissableDatePickerDialog(activityContext, activityContext.getResources().getIdentifier("CalendarDatePickerDialog", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, activityContext.getPackageName()), onDateSetListener, year, month, day);
                    break;
                case SPINNER:
                    dismissableDatePickerDialog = new DismissableDatePickerDialog(activityContext, activityContext.getResources().getIdentifier("SpinnerDatePickerDialog", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, activityContext.getPackageName()), onDateSetListener, year, month, day);
                    break;
                case DEFAULT:
                    dismissableDatePickerDialog = new DismissableDatePickerDialog(activityContext, onDateSetListener, year, month, day);
                    break;
            }
        }
        DatePicker datePicker = dismissableDatePickerDialog.getDatePicker();
        if (args == null || !args.containsKey("minDate")) {
            datePicker.setMinDate(DEFAULT_MIN_DATE);
        } else {
            c.setTimeInMillis(args.getLong("minDate"));
            c.set(11, 0);
            c.set(12, 0);
            c.set(13, 0);
            c.set(14, 0);
            datePicker.setMinDate(c.getTimeInMillis());
        }
        if (args != null && args.containsKey("maxDate")) {
            c.setTimeInMillis(args.getLong("maxDate"));
            c.set(11, 23);
            c.set(12, 59);
            c.set(13, 59);
            c.set(14, 999);
            datePicker.setMaxDate(c.getTimeInMillis());
        }
        return dismissableDatePickerDialog;
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss(dialog);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setOnDateSetListener(OnDateSetListener onDateSetListener) {
        this.mOnDateSetListener = onDateSetListener;
    }

    /* access modifiers changed from: 0000 */
    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }
}
