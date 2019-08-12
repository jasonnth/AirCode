package com.airbnb.android.core.net;

import android.content.SharedPreferences;
import android.os.Looper;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.events.BandwidthModeChangedEvent;
import com.airbnb.android.core.utils.BuildHelper;
import com.squareup.otto.Bus;

public class LowBandwidthManager {
    private static final String KEY_BANDWIDTH_MODE = "bandwidth_mode";
    private static final String KEY_SAVED_LOW_BANDWIDTH_MODE = "saved_low_bandwidth_mode";
    private static final String KEY_SEEN_LOW_BANDWIDTH_MESSAGE = "seen_low_bandwidth_message";
    private static final String KEY_SEEN_LOW_BANDWIDTH_SETTINGS = "seen_low_bandwidth_settings";
    private final Bus bus;
    private BandwidthMode currentMode;
    private final NetworkMonitor networkMonitor;
    private final AirbnbPreferences preferences;

    public LowBandwidthManager(AirbnbPreferences preferences2, Bus bus2, NetworkMonitor networkMonitor2) {
        this.preferences = preferences2;
        this.bus = bus2;
        this.networkMonitor = networkMonitor2;
    }

    public BandwidthMode getBandwidthMode() {
        if (this.currentMode == null) {
            this.currentMode = BandwidthMode.getBandwidthModeFromKey(getBandwidthSharedPreferences().getInt(KEY_BANDWIDTH_MODE, -1));
        }
        return this.currentMode;
    }

    public void setBandwidthMode(int selectedItem) {
        BandwidthMode mode = BandwidthMode.getBandwidthModeFromKey(selectedItem);
        this.currentMode = mode;
        getBandwidthSharedPreferences().edit().putInt(KEY_BANDWIDTH_MODE, mode.ordinal()).apply();
    }

    private SharedPreferences getBandwidthSharedPreferences() {
        return this.preferences.getGlobalSharedPreferences();
    }

    private boolean getSavedIsLowBandwidthMode() {
        return getBandwidthSharedPreferences().getBoolean(KEY_SAVED_LOW_BANDWIDTH_MODE, false);
    }

    private void setSavedIsLowBandwidthMode(boolean isLowBandwidth) {
        getBandwidthSharedPreferences().edit().putBoolean(KEY_SAVED_LOW_BANDWIDTH_MODE, isLowBandwidth).apply();
    }

    public boolean shouldShowLowBandwidthActivatedMessage() {
        SharedPreferences prefs = getBandwidthSharedPreferences();
        if (prefs.getBoolean(KEY_SEEN_LOW_BANDWIDTH_SETTINGS, false)) {
            return false;
        }
        if (System.currentTimeMillis() - prefs.getLong(KEY_SEEN_LOW_BANDWIDTH_MESSAGE, 0) > 86400000) {
            return true;
        }
        return false;
    }

    public boolean shouldForceLowBandwidth() {
        boolean isLowBandwidth;
        BandwidthMode mode = getBandwidthMode();
        if (!CoreApplication.instance().component().networkMonitor().isLowBandwidth() || (!BuildHelper.isFuture() && this.networkMonitor.getNetworkClass().ordinal() >= NetworkClass.TYPE_3G.ordinal())) {
            isLowBandwidth = false;
        } else {
            isLowBandwidth = true;
        }
        switch (mode) {
            case Low:
                return true;
            case High:
                return false;
            case LowWhileRoaming:
                if (this.networkMonitor.isRoaming() || isLowBandwidth) {
                    return true;
                }
                return false;
            default:
                if (getSavedIsLowBandwidthMode() != isLowBandwidth) {
                    setSavedIsLowBandwidthMode(isLowBandwidth);
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        this.bus.post(new BandwidthModeChangedEvent(isLowBandwidth));
                    }
                }
                return isLowBandwidth;
        }
    }

    public void markShowLowBandwidthActivatedMessageSeen() {
        getBandwidthSharedPreferences().edit().putLong(KEY_SEEN_LOW_BANDWIDTH_MESSAGE, System.currentTimeMillis()).apply();
    }

    public void markShowLowBandwidthActivatedSettingsVisited() {
        getBandwidthSharedPreferences().edit().putBoolean(KEY_SEEN_LOW_BANDWIDTH_SETTINGS, true).apply();
    }
}
