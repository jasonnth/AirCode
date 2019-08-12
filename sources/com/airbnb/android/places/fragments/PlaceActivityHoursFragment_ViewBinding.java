package com.airbnb.android.places.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.components.AirToolbar;

public class PlaceActivityHoursFragment_ViewBinding implements Unbinder {
    private PlaceActivityHoursFragment target;

    public PlaceActivityHoursFragment_ViewBinding(PlaceActivityHoursFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7627R.C7629id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7627R.C7629id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        PlaceActivityHoursFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
    }
}
