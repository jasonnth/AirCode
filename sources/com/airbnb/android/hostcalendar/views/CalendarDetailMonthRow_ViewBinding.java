package com.airbnb.android.hostcalendar.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.primitives.AirTextView;

public class CalendarDetailMonthRow_ViewBinding implements Unbinder {
    private CalendarDetailMonthRow target;

    public CalendarDetailMonthRow_ViewBinding(CalendarDetailMonthRow target2) {
        this(target2, target2);
    }

    public CalendarDetailMonthRow_ViewBinding(CalendarDetailMonthRow target2, View source) {
        this.target = target2;
        target2.monthText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.month_text, "field 'monthText'", AirTextView.class);
        target2.sectionDivider = Utils.findRequiredView(source, C6418R.C6420id.section_divider, "field 'sectionDivider'");
    }

    public void unbind() {
        CalendarDetailMonthRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.monthText = null;
        target2.sectionDivider = null;
    }
}
