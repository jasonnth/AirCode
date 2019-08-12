package com.airbnb.android.collections.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.HomeCollectionApplication;

final /* synthetic */ class SelectInvitationListingPickerController$$Lambda$1 implements OnClickListener {
    private final SelectInvitationListingPickerController arg$1;
    private final HomeCollectionApplication arg$2;

    private SelectInvitationListingPickerController$$Lambda$1(SelectInvitationListingPickerController selectInvitationListingPickerController, HomeCollectionApplication homeCollectionApplication) {
        this.arg$1 = selectInvitationListingPickerController;
        this.arg$2 = homeCollectionApplication;
    }

    public static OnClickListener lambdaFactory$(SelectInvitationListingPickerController selectInvitationListingPickerController, HomeCollectionApplication homeCollectionApplication) {
        return new SelectInvitationListingPickerController$$Lambda$1(selectInvitationListingPickerController, homeCollectionApplication);
    }

    public void onClick(View view) {
        this.arg$1.listener.onListingClicked(this.arg$2);
    }
}
