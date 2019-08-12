package com.airbnb.android.hostcalendar.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.primitives.AirTextView;

public class CalendarDetailHeaderRow_ViewBinding implements Unbinder {
    private CalendarDetailHeaderRow target;

    public CalendarDetailHeaderRow_ViewBinding(CalendarDetailHeaderRow target2) {
        this(target2, target2);
    }

    public CalendarDetailHeaderRow_ViewBinding(CalendarDetailHeaderRow target2, View source) {
        this.target = target2;
        target2.monthRow = (CalendarDetailMonthRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.month_header, "field 'monthRow'", CalendarDetailMonthRow.class);
        target2.todayClickable = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.today_nav, "field 'todayClickable'", AirTextView.class);
    }

    public void unbind() {
        CalendarDetailHeaderRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.monthRow = null;
        target2.todayClickable = null;
    }
}
