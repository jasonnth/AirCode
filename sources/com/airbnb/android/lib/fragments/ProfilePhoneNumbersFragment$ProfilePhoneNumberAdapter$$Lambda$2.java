package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ProfilePhoneNumber;

final /* synthetic */ class ProfilePhoneNumbersFragment$ProfilePhoneNumberAdapter$$Lambda$2 implements OnClickListener {
    private final ProfilePhoneNumberAdapter arg$1;
    private final ProfilePhoneNumber arg$2;

    private ProfilePhoneNumbersFragment$ProfilePhoneNumberAdapter$$Lambda$2(ProfilePhoneNumberAdapter profilePhoneNumberAdapter, ProfilePhoneNumber profilePhoneNumber) {
        this.arg$1 = profilePhoneNumberAdapter;
        this.arg$2 = profilePhoneNumber;
    }

    public static OnClickListener lambdaFactory$(ProfilePhoneNumberAdapter profilePhoneNumberAdapter, ProfilePhoneNumber profilePhoneNumber) {
        return new ProfilePhoneNumbersFragment$ProfilePhoneNumberAdapter$$Lambda$2(profilePhoneNumberAdapter, profilePhoneNumber);
    }

    public void onClick(View view) {
        ProfilePhoneNumberAdapter.lambda$getView$1(this.arg$1, this.arg$2, view);
    }
}
