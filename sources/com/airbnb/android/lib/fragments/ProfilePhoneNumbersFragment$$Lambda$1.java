package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ProfilePhoneNumbersFragment$$Lambda$1 implements OnClickListener {
    private final ProfilePhoneNumbersFragment arg$1;

    private ProfilePhoneNumbersFragment$$Lambda$1(ProfilePhoneNumbersFragment profilePhoneNumbersFragment) {
        this.arg$1 = profilePhoneNumbersFragment;
    }

    public static OnClickListener lambdaFactory$(ProfilePhoneNumbersFragment profilePhoneNumbersFragment) {
        return new ProfilePhoneNumbersFragment$$Lambda$1(profilePhoneNumbersFragment);
    }

    public void onClick(View view) {
        this.arg$1.handleAddPhoneNumber();
    }
}
