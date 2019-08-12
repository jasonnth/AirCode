package com.airbnb.android.collections.fragments;

import com.airbnb.android.core.models.HomeCollectionApplication;
import com.google.common.base.Predicate;

final /* synthetic */ class SelectInvitationListingPickerFragment$$Lambda$6 implements Predicate {
    private static final SelectInvitationListingPickerFragment$$Lambda$6 instance = new SelectInvitationListingPickerFragment$$Lambda$6();

    private SelectInvitationListingPickerFragment$$Lambda$6() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return SelectInvitationListingPickerFragment.lambda$handleListingsResponse$2((HomeCollectionApplication) obj);
    }
}
