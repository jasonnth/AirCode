package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.components.FlightTimeRowStyleApplier */
public final class FlightTimeRowStyleApplier extends StyleApplier<FlightTimeRowStyleApplier, FlightTimeRow> {
    public FlightTimeRowStyleApplier(FlightTimeRow view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_FlightTimeRow;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((FlightTimeRow) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_FlightTimeRow_n2_titleText)) {
            ((FlightTimeRow) getView()).setTitle(a.getString(R.styleable.n2_FlightTimeRow_n2_titleText));
        }
        if (a.hasValue(R.styleable.n2_FlightTimeRow_n2_flightDateText)) {
            ((FlightTimeRow) getView()).setFlightDateText(a.getString(R.styleable.n2_FlightTimeRow_n2_flightDateText));
        }
        if (a.hasValue(R.styleable.n2_FlightTimeRow_n2_flightTimeText)) {
            ((FlightTimeRow) getView()).setFlightTimeText(a.getString(R.styleable.n2_FlightTimeRow_n2_flightTimeText));
        }
        if (a.hasValue(R.styleable.n2_FlightTimeRow_n2_gateText)) {
            ((FlightTimeRow) getView()).setFlightGateText(a.getString(R.styleable.n2_FlightTimeRow_n2_gateText));
        }
        if (a.hasValue(R.styleable.n2_FlightTimeRow_n2_terminalText)) {
            ((FlightTimeRow) getView()).setFlightTerminalText(a.getString(R.styleable.n2_FlightTimeRow_n2_terminalText));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new BaseDividerComponentStyleApplier((BaseDividerComponent) getView()).apply(style);
    }
}
