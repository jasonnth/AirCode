package com.airbnb.android.collections.fragments;

import com.airbnb.android.core.responses.HomesCollectionsApplicationsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class SelectInvitationListingPickerFragment$$Lambda$1 implements Action1 {
    private final SelectInvitationListingPickerFragment arg$1;

    private SelectInvitationListingPickerFragment$$Lambda$1(SelectInvitationListingPickerFragment selectInvitationListingPickerFragment) {
        this.arg$1 = selectInvitationListingPickerFragment;
    }

    public static Action1 lambdaFactory$(SelectInvitationListingPickerFragment selectInvitationListingPickerFragment) {
        return new SelectInvitationListingPickerFragment$$Lambda$1(selectInvitationListingPickerFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleListingsResponse((HomesCollectionsApplicationsResponse) obj);
    }
}
