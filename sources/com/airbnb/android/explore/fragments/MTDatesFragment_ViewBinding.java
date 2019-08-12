package com.airbnb.android.explore.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.calendar.CalendarView;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.components.AirToolbar;

public class MTDatesFragment_ViewBinding implements Unbinder {
    private MTDatesFragment target;

    public MTDatesFragment_ViewBinding(MTDatesFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0857R.C0859id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.calendarView = (CalendarView) Utils.findRequiredViewAsType(source, C0857R.C0859id.calendar, "field 'calendarView'", CalendarView.class);
        target2.backgroundView = Utils.findRequiredView(source, C0857R.C0859id.background, "field 'backgroundView'");
    }

    public void unbind() {
        MTDatesFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.calendarView = null;
        target2.backgroundView = null;
    }
}
