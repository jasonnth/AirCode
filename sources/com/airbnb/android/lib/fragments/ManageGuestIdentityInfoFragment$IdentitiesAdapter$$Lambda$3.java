package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.interfaces.GuestIdentity;
import com.google.common.base.Predicate;

final /* synthetic */ class ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$3 implements Predicate {
    private static final ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$3 instance = new ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$3();

    private ManageGuestIdentityInfoFragment$IdentitiesAdapter$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((GuestIdentity) obj).isSelected();
    }
}
