package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.CheckInTimeOption;
import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class CheckInOutSettingsHelper$$Lambda$5 implements Listener {
    private final CheckInOutSettingsHelper arg$1;
    private final CheckInOutSettingsHelper.Listener arg$2;

    private CheckInOutSettingsHelper$$Lambda$5(CheckInOutSettingsHelper checkInOutSettingsHelper, CheckInOutSettingsHelper.Listener listener) {
        this.arg$1 = checkInOutSettingsHelper;
        this.arg$2 = listener;
    }

    public static Listener lambdaFactory$(CheckInOutSettingsHelper checkInOutSettingsHelper, CheckInOutSettingsHelper.Listener listener) {
        return new CheckInOutSettingsHelper$$Lambda$5(checkInOutSettingsHelper, listener);
    }

    public void itemSelected(Object obj) {
        CheckInOutSettingsHelper.lambda$null$5(this.arg$1, this.arg$2, (CheckInTimeOption) obj);
    }
}
