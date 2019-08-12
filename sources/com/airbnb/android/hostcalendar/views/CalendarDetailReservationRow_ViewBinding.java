package com.airbnb.android.hostcalendar.views;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;

public class CalendarDetailReservationRow_ViewBinding implements Unbinder {
    private CalendarDetailReservationRow target;

    public CalendarDetailReservationRow_ViewBinding(CalendarDetailReservationRow target2) {
        this(target2, target2);
    }

    public CalendarDetailReservationRow_ViewBinding(CalendarDetailReservationRow target2, View source) {
        this.target = target2;
        target2.rservationFrame = (FrameLayout) Utils.findRequiredViewAsType(source, C6418R.C6420id.reservation_frame, "field 'rservationFrame'", FrameLayout.class);
        target2.reservationBlock = (CalendarDetailReservationBlock) Utils.findRequiredViewAsType(source, C6418R.C6420id.reservation_block, "field 'reservationBlock'", CalendarDetailReservationBlock.class);
        target2.calendarRows = (LinearLayout) Utils.findRequiredViewAsType(source, C6418R.C6420id.calendar_rows, "field 'calendarRows'", LinearLayout.class);
        target2.verticalMargin = source.getContext().getResources().getDimensionPixelSize(C6418R.dimen.n2_vertical_padding_small);
    }

    public void unbind() {
        CalendarDetailReservationRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.rservationFrame = null;
        target2.reservationBlock = null;
        target2.calendarRows = null;
    }
}
