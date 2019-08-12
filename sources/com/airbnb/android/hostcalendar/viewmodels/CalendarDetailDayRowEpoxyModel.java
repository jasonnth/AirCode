package com.airbnb.android.hostcalendar.viewmodels;

import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.views.CalendarDetailDayRow;
import com.airbnb.android.hostcalendar.views.CalendarDetailDayRow.CalendarDetailDayClickListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public class CalendarDetailDayRowEpoxyModel extends AirEpoxyModel<CalendarDetailDayRow> {
    private CalendarDay calendarDay;
    private CalendarRule calendarRule;
    private CalendarDetailDayClickListener listener;
    private boolean selected;
    private boolean showTopSpace = true;

    public int getDefaultLayout() {
        return C6418R.layout.view_holder_calendar_detail_day_row;
    }

    public void bind(CalendarDetailDayRow row) {
        super.bind(row);
        row.showTopSpace(this.showTopSpace);
        row.bindCalendarDay(this.calendarDay, this.selected, this.calendarRule);
        if (this.listener != null) {
            row.setOnClickListener(CalendarDetailDayRowEpoxyModel$$Lambda$1.lambdaFactory$(this, row));
        } else {
            row.setOnClickListener(null);
        }
    }

    public void unbind(CalendarDetailDayRow row) {
        super.unbind(row);
        row.setOnClickListener(null);
        row.setSelected(false);
    }

    public CalendarDetailDayRowEpoxyModel showTopSpace(boolean show) {
        this.showTopSpace = show;
        return this;
    }

    public CalendarDetailDayRowEpoxyModel calendarDay(CalendarDay calendarDay2, CalendarRule calendarRule2) {
        this.calendarDay = calendarDay2;
        this.calendarRule = calendarRule2;
        return this;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public CalendarDetailDayRowEpoxyModel setSelected(boolean selected2) {
        this.selected = selected2;
        return this;
    }

    public CalendarDetailDayRowEpoxyModel clickListener(CalendarDetailDayClickListener listener2) {
        this.listener = listener2;
        return this;
    }
}
