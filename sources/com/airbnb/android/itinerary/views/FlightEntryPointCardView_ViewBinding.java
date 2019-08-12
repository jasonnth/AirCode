package com.airbnb.android.itinerary.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.p027n2.primitives.AirTextView;

public class FlightEntryPointCardView_ViewBinding implements Unbinder {
    private FlightEntryPointCardView target;

    public FlightEntryPointCardView_ViewBinding(FlightEntryPointCardView target2) {
        this(target2, target2);
    }

    public FlightEntryPointCardView_ViewBinding(FlightEntryPointCardView target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.title, "field 'title'", AirTextView.class);
        target2.acceptText = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.accept_text, "field 'acceptText'", AirTextView.class);
        target2.dismissText = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.dismiss_text, "field 'dismissText'", AirTextView.class);
    }

    public void unbind() {
        FlightEntryPointCardView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.acceptText = null;
        target2.dismissText = null;
    }
}
