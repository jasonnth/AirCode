package com.airbnb.android.listyourspacedls.adapters;

import com.airbnb.android.listing.utils.CheckInOutSettingsHelper.Listener;

final /* synthetic */ class CombinedAvailabilitySettingsAdapter$$Lambda$2 implements Listener {
    private final CombinedAvailabilitySettingsAdapter arg$1;

    private CombinedAvailabilitySettingsAdapter$$Lambda$2(CombinedAvailabilitySettingsAdapter combinedAvailabilitySettingsAdapter) {
        this.arg$1 = combinedAvailabilitySettingsAdapter;
    }

    public static Listener lambdaFactory$(CombinedAvailabilitySettingsAdapter combinedAvailabilitySettingsAdapter) {
        return new CombinedAvailabilitySettingsAdapter$$Lambda$2(combinedAvailabilitySettingsAdapter);
    }

    public void modelsUpdated() {
        this.arg$1.notifyModelsChanged();
    }
}
