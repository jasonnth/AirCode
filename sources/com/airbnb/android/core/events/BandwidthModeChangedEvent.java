package com.airbnb.android.core.events;

public class BandwidthModeChangedEvent {
    public final boolean mIsLowBandwidth;

    public BandwidthModeChangedEvent(boolean isLowBandwidth) {
        this.mIsLowBandwidth = isLowBandwidth;
    }
}
