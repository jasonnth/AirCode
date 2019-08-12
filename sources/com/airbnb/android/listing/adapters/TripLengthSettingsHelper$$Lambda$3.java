package com.airbnb.android.listing.adapters;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class TripLengthSettingsHelper$$Lambda$3 implements Listener {
    private final TripLengthSettingsHelper arg$1;
    private final TripLengthSettingsHelper.Listener arg$2;

    private TripLengthSettingsHelper$$Lambda$3(TripLengthSettingsHelper tripLengthSettingsHelper, TripLengthSettingsHelper.Listener listener) {
        this.arg$1 = tripLengthSettingsHelper;
        this.arg$2 = listener;
    }

    public static Listener lambdaFactory$(TripLengthSettingsHelper tripLengthSettingsHelper, TripLengthSettingsHelper.Listener listener) {
        return new TripLengthSettingsHelper$$Lambda$3(tripLengthSettingsHelper, listener);
    }

    public void amountChanged(Integer num) {
        TripLengthSettingsHelper.lambda$new$2(this.arg$1, this.arg$2, num);
    }
}
