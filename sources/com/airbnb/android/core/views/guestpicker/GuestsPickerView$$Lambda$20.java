package com.airbnb.android.core.views.guestpicker;

import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;

final /* synthetic */ class GuestsPickerView$$Lambda$20 implements OnValueChangedListener {
    private final GuestsPickerView arg$1;

    private GuestsPickerView$$Lambda$20(GuestsPickerView guestsPickerView) {
        this.arg$1 = guestsPickerView;
    }

    public static OnValueChangedListener lambdaFactory$(GuestsPickerView guestsPickerView) {
        return new GuestsPickerView$$Lambda$20(guestsPickerView);
    }

    public void onValueChanged(int i, int i2) {
        GuestsPickerView.lambda$new$5(this.arg$1, i, i2);
    }
}
