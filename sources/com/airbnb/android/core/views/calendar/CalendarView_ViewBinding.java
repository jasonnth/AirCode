package com.airbnb.android.core.views.calendar;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewStub;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.RangeDisplay;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirTextView;

public class CalendarView_ViewBinding implements Unbinder {
    private CalendarView target;

    public CalendarView_ViewBinding(CalendarView target2) {
        this(target2, target2);
    }

    public CalendarView_ViewBinding(CalendarView target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0716R.C0718id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.singleDayText = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.single_day_text, "field 'singleDayText'", AirTextView.class);
        target2.rangeDisplay = (RangeDisplay) Utils.findRequiredViewAsType(source, C0716R.C0718id.check_in_check_out_text, "field 'rangeDisplay'", RangeDisplay.class);
        target2.calendarView = (VerticalCalendarView) Utils.findRequiredViewAsType(source, C0716R.C0718id.vertical_calendar, "field 'calendarView'", VerticalCalendarView.class);
        target2.bottomBar = (ViewStub) Utils.findRequiredViewAsType(source, C0716R.C0718id.bottom_bar, "field 'bottomBar'", ViewStub.class);
        target2.progressView = Utils.findRequiredView(source, C0716R.C0718id.progress, "field 'progressView'");
        target2.sundayTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.sunday_text, "field 'sundayTextView'", AirTextView.class);
        target2.mondayTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.monday_text, "field 'mondayTextView'", AirTextView.class);
        target2.tuesdayTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.tuesday_text, "field 'tuesdayTextView'", AirTextView.class);
        target2.wednesdayTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.wednesday_text, "field 'wednesdayTextView'", AirTextView.class);
        target2.thursdayTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.thursday_text, "field 'thursdayTextView'", AirTextView.class);
        target2.fridayTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.friday_text, "field 'fridayTextView'", AirTextView.class);
        target2.saturdayTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.saturday_text, "field 'saturdayTextView'", AirTextView.class);
        target2.weekDaysDivider = Utils.findRequiredView(source, C0716R.C0718id.week_days_divider, "field 'weekDaysDivider'");
        Resources res = source.getContext().getResources();
        target2.startDateTitleString = res.getString(C0716R.string.check_in);
        target2.endDateTitleString = res.getString(C0716R.string.check_out);
        target2.dayOfWeekFormat = res.getString(C0716R.string.day_of_week_format);
        target2.dateFormat = res.getString(C0716R.string.date_name_format);
    }

    public void unbind() {
        CalendarView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.singleDayText = null;
        target2.rangeDisplay = null;
        target2.calendarView = null;
        target2.bottomBar = null;
        target2.progressView = null;
        target2.sundayTextView = null;
        target2.mondayTextView = null;
        target2.tuesdayTextView = null;
        target2.wednesdayTextView = null;
        target2.thursdayTextView = null;
        target2.fridayTextView = null;
        target2.saturdayTextView = null;
        target2.weekDaysDivider = null;
    }
}
