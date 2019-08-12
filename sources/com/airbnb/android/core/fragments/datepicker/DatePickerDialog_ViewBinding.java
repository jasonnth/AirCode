package com.airbnb.android.core.fragments.datepicker;

import android.view.View;
import android.widget.DatePicker;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class DatePickerDialog_ViewBinding implements Unbinder {
    private DatePickerDialog target;

    public DatePickerDialog_ViewBinding(DatePickerDialog target2, View source) {
        this.target = target2;
        target2.mDatePicker = (DatePicker) Utils.findRequiredViewAsType(source, C0716R.C0718id.datepicker, "field 'mDatePicker'", DatePicker.class);
    }

    public void unbind() {
        DatePickerDialog target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mDatePicker = null;
    }
}
