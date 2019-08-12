package com.airbnb.android.listing.utils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.listing.utils.AvailabilitySettingsHelper.Listener;

final /* synthetic */ class AvailabilitySettingsHelper$$Lambda$5 implements OnClickListener {
    private final AvailabilitySettingsHelper arg$1;
    private final Context arg$2;
    private final Listener arg$3;

    private AvailabilitySettingsHelper$$Lambda$5(AvailabilitySettingsHelper availabilitySettingsHelper, Context context, Listener listener) {
        this.arg$1 = availabilitySettingsHelper;
        this.arg$2 = context;
        this.arg$3 = listener;
    }

    public static OnClickListener lambdaFactory$(AvailabilitySettingsHelper availabilitySettingsHelper, Context context, Listener listener) {
        return new AvailabilitySettingsHelper$$Lambda$5(availabilitySettingsHelper, context, listener);
    }

    public void onClick(View view) {
        OptionsMenuFactory.forItems(this.arg$2, (T[]) MaxDaysNoticeSetting.values()).setTitleTransformer(AvailabilitySettingsHelper$$Lambda$6.lambdaFactory$(this.arg$2)).setListener(AvailabilitySettingsHelper$$Lambda$7.lambdaFactory$(this.arg$1, this.arg$3)).buildAndShow();
    }
}
