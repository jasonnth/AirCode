package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.TimePicker;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class TimePickerDialog_ViewBinding implements Unbinder {
    private TimePickerDialog target;

    public TimePickerDialog_ViewBinding(TimePickerDialog target2, View source) {
        this.target = target2;
        target2.mTimePicker = (TimePicker) Utils.findRequiredViewAsType(source, C0880R.C0882id.timepicker, "field 'mTimePicker'", TimePicker.class);
    }

    public void unbind() {
        TimePickerDialog target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mTimePicker = null;
    }
}
