package com.airbnb.android.hostcalendar.views;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;

public class HostCalendarReservationView_ViewBinding implements Unbinder {
    private HostCalendarReservationView target;

    public HostCalendarReservationView_ViewBinding(HostCalendarReservationView target2) {
        this(target2, target2);
    }

    public HostCalendarReservationView_ViewBinding(HostCalendarReservationView target2, View source) {
        this.target = target2;
        target2.calendarMonthsViewHolder = (LinearLayout) Utils.findRequiredViewAsType(source, C6418R.C6420id.calendar_month_view, "field 'calendarMonthsViewHolder'", LinearLayout.class);
        target2.moreNightsText = (TextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.more_nights, "field 'moreNightsText'", TextView.class);
    }

    public void unbind() {
        HostCalendarReservationView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.calendarMonthsViewHolder = null;
        target2.moreNightsText = null;
    }
}
