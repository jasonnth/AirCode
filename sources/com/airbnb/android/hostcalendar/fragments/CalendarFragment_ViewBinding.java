package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.components.LoadingView;

public class CalendarFragment_ViewBinding implements Unbinder {
    private CalendarFragment target;

    public CalendarFragment_ViewBinding(CalendarFragment target2, View source) {
        this.target = target2;
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C6418R.C6420id.loading_view, "field 'loadingView'", LoadingView.class);
        target2.contentContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C6418R.C6420id.content_container, "field 'contentContainer'", ViewGroup.class);
    }

    public void unbind() {
        CalendarFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loadingView = null;
        target2.contentContainer = null;
    }
}
