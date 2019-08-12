package com.airbnb.android.booking.fragments;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class BookingGuestsPickerFragment$1$$Lambda$1 implements OnCheckedChangeListener {
    private final C55591 arg$1;

    private BookingGuestsPickerFragment$1$$Lambda$1(C55591 r1) {
        this.arg$1 = r1;
    }

    public static OnCheckedChangeListener lambdaFactory$(C55591 r1) {
        return new BookingGuestsPickerFragment$1$$Lambda$1(r1);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        C55591.lambda$getPetStatusChangedListener$0(this.arg$1, switchRowInterface, z);
    }
}
