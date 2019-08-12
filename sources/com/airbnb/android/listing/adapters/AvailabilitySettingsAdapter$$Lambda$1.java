package com.airbnb.android.listing.adapters;

import com.airbnb.android.listing.utils.AvailabilitySettingsHelper.Listener;

final /* synthetic */ class AvailabilitySettingsAdapter$$Lambda$1 implements Listener {
    private final AvailabilitySettingsAdapter arg$1;

    private AvailabilitySettingsAdapter$$Lambda$1(AvailabilitySettingsAdapter availabilitySettingsAdapter) {
        this.arg$1 = availabilitySettingsAdapter;
    }

    public static Listener lambdaFactory$(AvailabilitySettingsAdapter availabilitySettingsAdapter) {
        return new AvailabilitySettingsAdapter$$Lambda$1(availabilitySettingsAdapter);
    }

    public void modelsUpdated() {
        this.arg$1.notifyModelsChanged();
    }
}
