package com.airbnb.android.itinerary.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.p027n2.collections.AirRecyclerView;

public class FlightReservationObjectFragment_ViewBinding implements Unbinder {
    private FlightReservationObjectFragment target;

    public FlightReservationObjectFragment_ViewBinding(FlightReservationObjectFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C5755R.C5757id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
    }

    public void unbind() {
        FlightReservationObjectFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
    }
}
