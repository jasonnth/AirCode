package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.lib.C0880R;

public class TimePickerDialog extends ZenDialog {
    public static final String ARG_TIME_HOUR = "time_hour";
    public static final String ARG_TIME_MINUTE = "time_minute";
    public static final int TIME_PICKER_CANCEL = 3001;
    public static final int TIME_PICKER_OK = 3002;
    @BindView
    TimePicker mTimePicker;

    public static TimePickerDialog newInstance(int initialHour, int initialMinute, Fragment targetFragment) {
        TimePickerDialog dialog = (TimePickerDialog) new ZenBuilder(new TimePickerDialog()).withTitle(C0880R.string.select_time).withCustomLayout().withDualButton(C0880R.string.cancel, (int) TIME_PICKER_CANCEL, C0880R.string.okay, (int) TIME_PICKER_OK, targetFragment).create();
        Bundle args = dialog.getArguments();
        args.putInt(ARG_TIME_HOUR, initialHour);
        args.putInt(ARG_TIME_MINUTE, initialMinute);
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setCustomView(inflater.inflate(C0880R.layout.dialog_fragment_time_picker, container, false));
        ButterKnife.bind(this, view);
        this.mTimePicker.setIs24HourView(Boolean.valueOf(DateHelper.is24Hour(container.getContext())));
        Bundle args = getArguments();
        int hour = args.getInt(ARG_TIME_HOUR, -1);
        if (hour != -1) {
            this.mTimePicker.setCurrentHour(Integer.valueOf(hour));
        }
        int minute = args.getInt(ARG_TIME_MINUTE, -1);
        if (minute != -1) {
            this.mTimePicker.setCurrentMinute(Integer.valueOf(minute));
        }
        return view;
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        Intent data = new Intent();
        int hour = this.mTimePicker.getCurrentHour().intValue();
        int minute = this.mTimePicker.getCurrentMinute().intValue();
        data.putExtra(ARG_TIME_HOUR, hour);
        data.putExtra(ARG_TIME_MINUTE, minute);
        sendActivityResult(requestCodeRight, -1, data);
    }
}
