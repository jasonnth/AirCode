package com.airbnb.android.lib.china5a.fragments;

import com.airbnb.android.core.fragments.CountryCodeSelectionFragment.CountrySelectedListener;
import com.airbnb.android.core.presenters.CountryCodeItem;

final /* synthetic */ class PhoneVerificationFragment$$Lambda$9 implements CountrySelectedListener {
    private final PhoneVerificationFragment arg$1;

    private PhoneVerificationFragment$$Lambda$9(PhoneVerificationFragment phoneVerificationFragment) {
        this.arg$1 = phoneVerificationFragment;
    }

    public static CountrySelectedListener lambdaFactory$(PhoneVerificationFragment phoneVerificationFragment) {
        return new PhoneVerificationFragment$$Lambda$9(phoneVerificationFragment);
    }

    public void onCountrySelected(CountryCodeItem countryCodeItem) {
        PhoneVerificationFragment.lambda$null$0(this.arg$1, countryCodeItem);
    }
}
