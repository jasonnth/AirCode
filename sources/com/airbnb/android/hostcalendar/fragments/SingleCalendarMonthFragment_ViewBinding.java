package com.airbnb.android.hostcalendar.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.components.InlineTipRow;

public class SingleCalendarMonthFragment_ViewBinding implements Unbinder {
    private SingleCalendarMonthFragment target;

    public SingleCalendarMonthFragment_ViewBinding(SingleCalendarMonthFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C6418R.C6420id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.inlineTipRow = (InlineTipRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.tip_row, "field 'inlineTipRow'", InlineTipRow.class);
        target2.tab_bar_height = source.getContext().getResources().getDimensionPixelSize(C6418R.dimen.tab_bar_height);
    }

    public void unbind() {
        SingleCalendarMonthFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.inlineTipRow = null;
    }
}
