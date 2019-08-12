package com.airbnb.android.collections.fragments;

import com.airbnb.android.collections.adapters.SelectInvitationListingPickerController.OnListingClickedListener;
import com.airbnb.android.core.models.HomeCollectionApplication;

final /* synthetic */ class SelectInvitationListingPickerFragment$$Lambda$5 implements OnListingClickedListener {
    private final SelectInvitationListingPickerFragment arg$1;

    private SelectInvitationListingPickerFragment$$Lambda$5(SelectInvitationListingPickerFragment selectInvitationListingPickerFragment) {
        this.arg$1 = selectInvitationListingPickerFragment;
    }

    public static OnListingClickedListener lambdaFactory$(SelectInvitationListingPickerFragment selectInvitationListingPickerFragment) {
        return new SelectInvitationListingPickerFragment$$Lambda$5(selectInvitationListingPickerFragment);
    }

    public void onListingClicked(HomeCollectionApplication homeCollectionApplication) {
        this.arg$1.handleOnListingClicked(homeCollectionApplication);
    }
}
