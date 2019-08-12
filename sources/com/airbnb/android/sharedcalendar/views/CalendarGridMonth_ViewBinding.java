package com.airbnb.android.sharedcalendar.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.sharedcalendar.C1576R;

public class CalendarGridMonth_ViewBinding implements Unbinder {
    private CalendarGridMonth target;

    public CalendarGridMonth_ViewBinding(CalendarGridMonth target2) {
        this(target2, target2);
    }

    public CalendarGridMonth_ViewBinding(CalendarGridMonth target2, View source) {
        this.target = target2;
        target2.monthHeader = (CalendarGridMonthHeader) Utils.findRequiredViewAsType(source, C1576R.C1578id.month_header, "field 'monthHeader'", CalendarGridMonthHeader.class);
        target2.rows = (CalendarGridRow[]) Utils.arrayOf((CalendarGridRow) Utils.findRequiredViewAsType(source, C1576R.C1578id.row_1, "field 'rows'", CalendarGridRow.class), (CalendarGridRow) Utils.findRequiredViewAsType(source, C1576R.C1578id.row_2, "field 'rows'", CalendarGridRow.class), (CalendarGridRow) Utils.findRequiredViewAsType(source, C1576R.C1578id.row_3, "field 'rows'", CalendarGridRow.class), (CalendarGridRow) Utils.findRequiredViewAsType(source, C1576R.C1578id.row_4, "field 'rows'", CalendarGridRow.class), (CalendarGridRow) Utils.findRequiredViewAsType(source, C1576R.C1578id.row_5, "field 'rows'", CalendarGridRow.class), (CalendarGridRow) Utils.findRequiredViewAsType(source, C1576R.C1578id.row_6, "field 'rows'", CalendarGridRow.class));
    }

    public void unbind() {
        CalendarGridMonth target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.monthHeader = null;
        target2.rows = null;
    }
}
