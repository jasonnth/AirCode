package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.FlightTimeRow_ViewBinding */
public class FlightTimeRow_ViewBinding implements Unbinder {
    private FlightTimeRow target;

    public FlightTimeRow_ViewBinding(FlightTimeRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.flightTimeText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.flight_time, "field 'flightTimeText'", AirTextView.class);
        target2.flightDateText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.flight_date, "field 'flightDateText'", AirTextView.class);
        target2.gateText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.gate, "field 'gateText'", AirTextView.class);
        target2.terminalText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.terminal, "field 'terminalText'", AirTextView.class);
    }

    public void unbind() {
        FlightTimeRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.flightTimeText = null;
        target2.flightDateText = null;
        target2.gateText = null;
        target2.terminalText = null;
    }
}
