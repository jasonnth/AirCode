package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class AvailabilitySettingsHelper$$Lambda$7 implements Listener {
    private final AvailabilitySettingsHelper arg$1;
    private final AvailabilitySettingsHelper.Listener arg$2;

    private AvailabilitySettingsHelper$$Lambda$7(AvailabilitySettingsHelper availabilitySettingsHelper, AvailabilitySettingsHelper.Listener listener) {
        this.arg$1 = availabilitySettingsHelper;
        this.arg$2 = listener;
    }

    public static Listener lambdaFactory$(AvailabilitySettingsHelper availabilitySettingsHelper, AvailabilitySettingsHelper.Listener listener) {
        return new AvailabilitySettingsHelper$$Lambda$7(availabilitySettingsHelper, listener);
    }

    public void itemSelected(Object obj) {
        AvailabilitySettingsHelper.lambda$null$13(this.arg$1, this.arg$2, (MaxDaysNoticeSetting) obj);
    }
}