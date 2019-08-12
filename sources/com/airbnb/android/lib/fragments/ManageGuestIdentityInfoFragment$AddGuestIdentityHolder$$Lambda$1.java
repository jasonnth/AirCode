package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.activities.AutoAirModalLargeActivity;

final /* synthetic */ class ManageGuestIdentityInfoFragment$AddGuestIdentityHolder$$Lambda$1 implements OnClickListener {
    private final AddGuestIdentityHolder arg$1;

    private ManageGuestIdentityInfoFragment$AddGuestIdentityHolder$$Lambda$1(AddGuestIdentityHolder addGuestIdentityHolder) {
        this.arg$1 = addGuestIdentityHolder;
    }

    public static OnClickListener lambdaFactory$(AddGuestIdentityHolder addGuestIdentityHolder) {
        return new ManageGuestIdentityInfoFragment$AddGuestIdentityHolder$$Lambda$1(addGuestIdentityHolder);
    }

    public void onClick(View view) {
        ManageGuestIdentityInfoFragment.this.startActivityForResult(AutoAirModalLargeActivity.intentForFragment(ManageGuestIdentityInfoFragment.this.getActivity(), CreateGuestIdentityFragment.class, null), 2000);
    }
}
