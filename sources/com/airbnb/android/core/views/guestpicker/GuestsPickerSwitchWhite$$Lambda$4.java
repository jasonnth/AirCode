package com.airbnb.android.core.views.guestpicker;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GuestsPickerSwitchWhite$$Lambda$4 implements OnClickListener {
    private final GuestsPickerSwitchWhite arg$1;

    private GuestsPickerSwitchWhite$$Lambda$4(GuestsPickerSwitchWhite guestsPickerSwitchWhite) {
        this.arg$1 = guestsPickerSwitchWhite;
    }

    public static OnClickListener lambdaFactory$(GuestsPickerSwitchWhite guestsPickerSwitchWhite) {
        return new GuestsPickerSwitchWhite$$Lambda$4(guestsPickerSwitchWhite);
    }

    public void onClick(View view) {
        this.arg$1.toggle();
    }
}
