package com.airbnb.android.itinerary.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;

public class TripFragment_ViewBinding implements Unbinder {
    private TripFragment target;

    public TripFragment_ViewBinding(TripFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5755R.C5757id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C5755R.C5757id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C5755R.C5757id.loading_view, "field 'loadingView'", LoadingView.class);
    }

    public void unbind() {
        TripFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.loadingView = null;
    }
}
