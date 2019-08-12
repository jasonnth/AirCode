package com.airbnb.android.core.views.guestpicker;

import com.airbnb.android.core.views.guestpicker.GuestsPickerSwitchWhite.OnCheckedChangeListener;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;

final /* synthetic */ class GuestsPickerSwitchRowWhite$$Lambda$1 implements OnCheckedChangeListener {
    private final GuestsPickerSwitchRowWhite arg$1;
    private final SwitchRowInterface.OnCheckedChangeListener arg$2;

    private GuestsPickerSwitchRowWhite$$Lambda$1(GuestsPickerSwitchRowWhite guestsPickerSwitchRowWhite, SwitchRowInterface.OnCheckedChangeListener onCheckedChangeListener) {
        this.arg$1 = guestsPickerSwitchRowWhite;
        this.arg$2 = onCheckedChangeListener;
    }

    public static OnCheckedChangeListener lambdaFactory$(GuestsPickerSwitchRowWhite guestsPickerSwitchRowWhite, SwitchRowInterface.OnCheckedChangeListener onCheckedChangeListener) {
        return new GuestsPickerSwitchRowWhite$$Lambda$1(guestsPickerSwitchRowWhite, onCheckedChangeListener);
    }

    public void onCheckedChanged(GuestsPickerSwitchWhite guestsPickerSwitchWhite, boolean z) {
        this.arg$2.onCheckedChanged(this.arg$1, z);
    }
}
