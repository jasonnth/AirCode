package com.airbnb.android.lib.fragments;

import android.view.View;
import com.airbnb.android.lib.views.LinearListView;
import com.airbnb.android.lib.views.LinearListView.OnItemClickListener;

final /* synthetic */ class ProfilePhoneNumbersFragment$$Lambda$2 implements OnItemClickListener {
    private final ProfilePhoneNumbersFragment arg$1;

    private ProfilePhoneNumbersFragment$$Lambda$2(ProfilePhoneNumbersFragment profilePhoneNumbersFragment) {
        this.arg$1 = profilePhoneNumbersFragment;
    }

    public static OnItemClickListener lambdaFactory$(ProfilePhoneNumbersFragment profilePhoneNumbersFragment) {
        return new ProfilePhoneNumbersFragment$$Lambda$2(profilePhoneNumbersFragment);
    }

    public void onItemClick(LinearListView linearListView, View view, int i, long j) {
        ProfilePhoneNumbersFragment.lambda$setupPhoneNumbersList$1(this.arg$1, linearListView, view, i, j);
    }
}
