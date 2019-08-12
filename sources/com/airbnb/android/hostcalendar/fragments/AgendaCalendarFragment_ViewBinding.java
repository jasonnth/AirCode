package com.airbnb.android.hostcalendar.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;

public class AgendaCalendarFragment_ViewBinding implements Unbinder {
    private AgendaCalendarFragment target;

    public AgendaCalendarFragment_ViewBinding(AgendaCalendarFragment target2, View source) {
        this.target = target2;
        target2.swipeRefreshLayout = (AirSwipeRefreshLayout) Utils.findRequiredViewAsType(source, C6418R.C6420id.swipe_refresh_layout, "field 'swipeRefreshLayout'", AirSwipeRefreshLayout.class);
        target2.agendaRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C6418R.C6420id.recycler_view, "field 'agendaRecyclerView'", RecyclerView.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C6418R.C6420id.loading_view, "field 'loadingView'", LoadingView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6418R.C6420id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        AgendaCalendarFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.swipeRefreshLayout = null;
        target2.agendaRecyclerView = null;
        target2.loadingView = null;
        target2.toolbar = null;
    }
}
