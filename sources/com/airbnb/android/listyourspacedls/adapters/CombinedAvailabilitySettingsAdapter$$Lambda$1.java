package com.airbnb.android.listyourspacedls.adapters;

import com.airbnb.android.listing.utils.AvailabilitySettingsHelper.Listener;

final /* synthetic */ class CombinedAvailabilitySettingsAdapter$$Lambda$1 implements Listener {
    private final CombinedAvailabilitySettingsAdapter arg$1;

    private CombinedAvailabilitySettingsAdapter$$Lambda$1(CombinedAvailabilitySettingsAdapter combinedAvailabilitySettingsAdapter) {
        this.arg$1 = combinedAvailabilitySettingsAdapter;
    }

    public static Listener lambdaFactory$(CombinedAvailabilitySettingsAdapter combinedAvailabilitySettingsAdapter) {
        return new CombinedAvailabilitySettingsAdapter$$Lambda$1(combinedAvailabilitySettingsAdapter);
    }

    public void modelsUpdated() {
        this.arg$1.notifyModelsChanged();
    }
}
