package com.airbnb.android.hostcalendar.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.views.CalendarDetailHeaderRow;

public class SingleCalendarDetailFragment_ViewBinding implements Unbinder {
    private SingleCalendarDetailFragment target;

    public SingleCalendarDetailFragment_ViewBinding(SingleCalendarDetailFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C6418R.C6420id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.headerRow = (CalendarDetailHeaderRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.header_row, "field 'headerRow'", CalendarDetailHeaderRow.class);
        target2.paddingFromOverlappingReservation = source.getContext().getResources().getDimensionPixelSize(C6418R.dimen.n2_vertical_padding_small);
    }

    public void unbind() {
        SingleCalendarDetailFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.headerRow = null;
    }
}
