package com.airbnb.android.hostcalendar.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CalendarNestedBusyDayFragment_ViewBinding implements Unbinder {
    private CalendarNestedBusyDayFragment target;
    private View view2131755199;

    public CalendarNestedBusyDayFragment_ViewBinding(final CalendarNestedBusyDayFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6418R.C6420id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C6418R.C6420id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C6418R.C6420id.button, "field 'button' and method 'buttonClicked'");
        target2.button = (AirButton) Utils.castView(view, C6418R.C6420id.button, "field 'button'", AirButton.class);
        this.view2131755199 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.buttonClicked();
            }
        });
    }

    public void unbind() {
        CalendarNestedBusyDayFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.button = null;
        this.view2131755199.setOnClickListener(null);
        this.view2131755199 = null;
    }
}
