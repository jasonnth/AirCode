package com.airbnb.android.core.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PhoneNumberInputSheet$$Lambda$4 implements OnClickListener {
    private final PhoneNumberInputSheet arg$1;

    private PhoneNumberInputSheet$$Lambda$4(PhoneNumberInputSheet phoneNumberInputSheet) {
        this.arg$1 = phoneNumberInputSheet;
    }

    public static OnClickListener lambdaFactory$(PhoneNumberInputSheet phoneNumberInputSheet) {
        return new PhoneNumberInputSheet$$Lambda$4(phoneNumberInputSheet);
    }

    public void onClick(View view) {
        PhoneNumberInputSheet.lambda$setUpCallingCodeButton$1(this.arg$1, view);
    }
}
