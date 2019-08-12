package com.airbnb.android.core.net;

import com.airbnb.android.core.C0716R;

public enum BandwidthMode {
    Auto(C0716R.string.title_bandwidth_mode_auto),
    High(C0716R.string.title_bandwidth_mode_high),
    LowWhileRoaming(C0716R.string.title_low_while_roaming),
    Low(C0716R.string.title_bandwidth_mode_low);
    
    public final int mTitleRes;

    private BandwidthMode(int titleRes) {
        this.mTitleRes = titleRes;
    }

    public static BandwidthMode getBandwidthModeFromKey(int bandwidthKey) {
        if (bandwidthKey < 0 || bandwidthKey >= values().length) {
            return Auto;
        }
        return values()[bandwidthKey];
    }
}
