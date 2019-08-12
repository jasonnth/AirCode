package com.airbnb.android.collections.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SelectInvitationListingPickerFragment$$Lambda$7 implements OnClickListener {
    private final SelectInvitationListingPickerFragment arg$1;

    private SelectInvitationListingPickerFragment$$Lambda$7(SelectInvitationListingPickerFragment selectInvitationListingPickerFragment) {
        this.arg$1 = selectInvitationListingPickerFragment;
    }

    public static OnClickListener lambdaFactory$(SelectInvitationListingPickerFragment selectInvitationListingPickerFragment) {
        return new SelectInvitationListingPickerFragment$$Lambda$7(selectInvitationListingPickerFragment);
    }

    public void onClick(View view) {
        this.arg$1.makeListingsRequest();
    }
}
