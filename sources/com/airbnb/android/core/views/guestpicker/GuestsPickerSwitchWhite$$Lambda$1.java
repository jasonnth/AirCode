package com.airbnb.android.core.views.guestpicker;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class GuestsPickerSwitchWhite$$Lambda$1 implements AnimatorUpdateListener {
    private final GuestsPickerSwitchWhite arg$1;

    private GuestsPickerSwitchWhite$$Lambda$1(GuestsPickerSwitchWhite guestsPickerSwitchWhite) {
        this.arg$1 = guestsPickerSwitchWhite;
    }

    public static AnimatorUpdateListener lambdaFactory$(GuestsPickerSwitchWhite guestsPickerSwitchWhite) {
        return new GuestsPickerSwitchWhite$$Lambda$1(guestsPickerSwitchWhite);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        GuestsPickerSwitchWhite.lambda$new$0(this.arg$1, valueAnimator);
    }
}