package com.airbnb.android.core.views.guestpicker;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class GuestsPickerView$$Lambda$21 implements OnCheckedChangeListener {
    private final GuestsPickerView arg$1;

    private GuestsPickerView$$Lambda$21(GuestsPickerView guestsPickerView) {
        this.arg$1 = guestsPickerView;
    }

    public static OnCheckedChangeListener lambdaFactory$(GuestsPickerView guestsPickerView) {
        return new GuestsPickerView$$Lambda$21(guestsPickerView);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        GuestsPickerView.lambda$new$6(this.arg$1, switchRowInterface, z);
    }
}
