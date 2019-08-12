package com.airbnb.android.core.views;

import com.airbnb.android.core.interfaces.CallingCodeSelectedListener;
import com.airbnb.android.core.models.CallingCodeEntry;
import com.airbnb.android.core.presenters.CountryCodeItem;

final /* synthetic */ class PhoneNumberInputSheet$$Lambda$2 implements CallingCodeSelectedListener {
    private final PhoneNumberInputSheet arg$1;

    private PhoneNumberInputSheet$$Lambda$2(PhoneNumberInputSheet phoneNumberInputSheet) {
        this.arg$1 = phoneNumberInputSheet;
    }

    public static CallingCodeSelectedListener lambdaFactory$(PhoneNumberInputSheet phoneNumberInputSheet) {
        return new PhoneNumberInputSheet$$Lambda$2(phoneNumberInputSheet);
    }

    public void onSelectedCallingCode(CallingCodeEntry callingCodeEntry) {
        this.arg$1.onNewCountryCodeSelected(new CountryCodeItem(callingCodeEntry.getCallingCode(), callingCodeEntry.getCountryCode(), callingCodeEntry.getDisplayCountryName()));
    }
}
