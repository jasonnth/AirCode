package com.airbnb.android.core.views.guestpicker;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GuestsPickerStepperRowWhite$$Lambda$1 implements OnClickListener {
    private final GuestsPickerStepperRowWhite arg$1;

    private GuestsPickerStepperRowWhite$$Lambda$1(GuestsPickerStepperRowWhite guestsPickerStepperRowWhite) {
        this.arg$1 = guestsPickerStepperRowWhite;
    }

    public static OnClickListener lambdaFactory$(GuestsPickerStepperRowWhite guestsPickerStepperRowWhite) {
        return new GuestsPickerStepperRowWhite$$Lambda$1(guestsPickerStepperRowWhite);
    }

    public void onClick(View view) {
        this.arg$1.handleValueChange(this.arg$1.value + 1);
    }
}
