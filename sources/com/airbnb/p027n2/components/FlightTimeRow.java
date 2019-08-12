package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.FlightTimeRow */
public class FlightTimeRow extends BaseDividerComponent {
    @BindView
    AirTextView flightDateText;
    @BindView
    AirTextView flightTimeText;
    @BindView
    AirTextView gateText;
    @BindView
    AirTextView terminalText;
    @BindView
    AirTextView titleText;

    public FlightTimeRow(Context context) {
        super(context);
    }

    public FlightTimeRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlightTimeRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_flight_time_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    public void setTitle(String title) {
        this.titleText.setText(title);
    }

    public void setTitle(int title) {
        this.titleText.setText(title);
    }

    public void setFlightDateText(String flightDate) {
        this.flightDateText.setText(flightDate);
    }

    public void setFlightTimeText(String flightTime) {
        this.flightTimeText.setText(flightTime);
    }

    public void setFlightGateText(String gate) {
        this.gateText.setText(gate);
    }

    public void setFlightTerminalText(String terminal) {
        this.terminalText.setText(terminal);
    }

    public static void mock(FlightTimeRow row) {
        row.setTitle("Departure");
        row.setFlightGateText("L3");
        row.setFlightTerminalText("2");
        row.setFlightDateText("WED JULY 3");
        row.setFlightTimeText("12:30 AM");
    }
}
