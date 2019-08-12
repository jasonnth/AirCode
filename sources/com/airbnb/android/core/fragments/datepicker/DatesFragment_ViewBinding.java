package com.airbnb.android.core.fragments.datepicker;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.calendar.CalendarView;
import com.airbnb.p027n2.components.AirToolbar;

public class DatesFragment_ViewBinding implements Unbinder {
    private DatesFragment target;

    public DatesFragment_ViewBinding(DatesFragment target2, View source) {
        this.target = target2;
        target2.calendarView = (CalendarView) Utils.findRequiredViewAsType(source, C0716R.C0718id.calendar, "field 'calendarView'", CalendarView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.container = Utils.findRequiredView(source, C0716R.C0718id.container, "field 'container'");
    }

    public void unbind() {
        DatesFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.calendarView = null;
        target2.toolbar = null;
        target2.container = null;
    }
}
